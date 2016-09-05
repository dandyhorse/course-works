package org.lenteam.colmen.controllers;

import org.lenteam.colmen.entities.DemoPersonEntity;
import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.services.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Mickey on 05.09.2016.
 */
@RestController
@RequestMapping("")
public class PersonsController {

    @Autowired
    CommonUserService service;

    public PersonsController(CommonUserService service) {
        this.service = service;
    }

    //Запрос: GET /persons выводит весь список

    @RequestMapping(path = "/persons", method = GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<PersonEntity> getAll() { return service.getAllPersons();  }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addPerson(@RequestParam(defaultValue = "Melone") String name) { service.savePerson(name);}

}
