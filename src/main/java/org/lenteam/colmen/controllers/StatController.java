package org.lenteam.colmen.controllers;

import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.services.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Mickey on 05.09.2016.
 */
/*
демо контроллер для тренировки
 */
@RestController
@RequestMapping("/stat")
public class StatController {

    @Autowired
    CommonUserService service;

    public StatController (CommonUserService service) { this.service = service; }

    @RequestMapping(path = "/persons/onsite/{id}", method = GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<PersonEntity> getall(Long id) { return service.getPersons(id); }
}
