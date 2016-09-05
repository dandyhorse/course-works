package org.lenteam.colmen.controllers;

import org.lenteam.colmen.entities.DemoPersonEntity;
import org.lenteam.colmen.services.DemoPersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Mickey on 05.09.2016.
 */
@RestController
@RequestMapping("")
public class PersonsController {

    @Autowired
    DemoPersonsService service;

    public PersonsController(DemoPersonsService service) {
        this.service = service;
    }

    //Запрос: GET /persons выводит весь список

    @RequestMapping(path = "/persons", method = GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Hashtable<Long, DemoPersonEntity> getAll() {
        return service.getAll();
    }

}
