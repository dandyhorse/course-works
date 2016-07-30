package com.epam.memorina.services;

import com.epam.memorina.entities.UserEntity;
import com.epam.memorina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Solovev Anton
 * @since 30.07.2016.
 */
@Service("defaultService")
public class DefaultUserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> loadUsers() {
        List<UserEntity> userEntityList = (List<UserEntity>) userRepository.findAll();
        return userEntityList;
    }

    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

}
