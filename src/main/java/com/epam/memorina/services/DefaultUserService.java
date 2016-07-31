package com.epam.memorina.services;

import com.epam.memorina.assemblers.Assembler;
import com.epam.memorina.entities.UserEntity;
import com.epam.memorina.exceptions.ServiceException;
import com.epam.memorina.models.User;
import com.epam.memorina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Solovev Anton
 * @since 30.07.2016.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
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
        insideException(() -> userRepository.save(userEntity));
    }

    private <T> T insideException(Supplier<T> function) {
        try {
            return function.get();
        } catch (DataAccessException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
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
