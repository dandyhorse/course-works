package org.lenteam.colmen.controllers;

import org.lenteam.colmen.entities.DemoPersonEntity;
import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.services.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Mickey on 05.09.2016.
 */
@RestController
public class PersonsController {

    @Autowired
    CommonUserService service;

    public PersonsController(CommonUserService service) {
        this.service = service;
    }

    //Запрос: GET /persons выводит весь список
    @RequestMapping(path = "/persons", method = GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<PersonEntity> getAllPersons() { return service.getAllPersons();  }

    //Запрос: GET stat/persons/onsite/id,  выводит список имен по конкретному сайту. id - id сайта
    @RequestMapping(path = "/stat/persons/onsite/{id}", method = GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<PersonEntity> getPersonsOnSite(SiteEntity id) { return service.getPersonsOnSite(id); }

    // добавляет имя методом POST
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addPerson(@RequestParam String name) { service.savePerson(name);}

    // удаляет персону методом DEELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable("id") Long id) {service.deletePerson(id);}

}
