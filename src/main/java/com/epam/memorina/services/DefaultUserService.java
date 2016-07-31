package com.epam.memorina.services;

import com.epam.memorina.assemblers.Assembler;
import com.epam.memorina.entities.UserEntity;
import com.epam.memorina.models.User;
import com.epam.memorina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Solovev Anton
 * @since 30.07.2016.
 */
@Service
@Transactional
public class DefaultUserService {

    private UserRepository userRepository;
    private Assembler<User, UserEntity> assembler;

    public DefaultUserService(@Autowired UserRepository userRepository,
                              @Autowired Assembler<User, UserEntity> assembler) {
        this.userRepository = userRepository;
        this.assembler = assembler;
    }

    public List<User> loadAll() {
        List<UserEntity> allEntities = (List<UserEntity>) userRepository.findAll();
        return allEntities
                .stream()
                .map(this::newModel)
                .collect(Collectors.toList());
    }

    public void save(User user) {
        UserEntity userEntity = newEntity(user);
        userRepository.save(userEntity);
    }

    public User loadByName(String username) {
        UserEntity user = userRepository.findByUsername(username);
        return newModel(user);
    }

    public boolean validUser(User user) {
        User userFromRepository = loadByName(user.getUsername());
        return user.getPassword().equals(userFromRepository.getPassword());
    }

    private User newModel(UserEntity entity) {
        return assembler.newModel(entity);
    }

    private UserEntity newEntity(User user) {
        return assembler.newEntity(user);
    }
}
