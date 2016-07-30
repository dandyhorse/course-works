package com.epam.memorina.controllers;

import com.epam.memorina.entities.UserEntity;
import com.epam.memorina.services.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Solovev Anton
 * @since 30.07.2016.
 */
@RestController
@RequestMapping(value = "/users")
public class UserRestController {

    @Autowired
    private DefaultUserService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<UserEntity> userGet() {
        return service.loadUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void userPost(@RequestParam(value = "name") String name) {
        service.saveUser(new UserEntity(name));
    }


}
