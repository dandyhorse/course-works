package com.epam.memorina.controllers;

import com.epam.memorina.models.GameStatistic;
import com.epam.memorina.models.User;
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

    private DefaultUserService service;

    @Autowired
    public UserRestController(DefaultUserService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUser() {
        return service.loadAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestParam(value = "name") String name,
                           @RequestParam(value = "password", defaultValue = "default") String pass) {
        User user = new User(name, pass, new GameStatistic());
        service.save(user);
    }


}
