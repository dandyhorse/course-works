package org.lenteam.colmen.controllers;

import org.lenteam.colmen.entities.KeywordEntity;
import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.services.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Mickey on 06.09.2016.
 */
public class KeywordController {

    @Autowired
    CommonUserService service;

    public KeywordController(CommonUserService service) { this.service = service; }

    //Запрос: GET keyword/person_id,  person_id - идентификатор персоны, выводит список ключевых слов
    @RequestMapping(path = "/keyword/{person_id}", method = GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<KeywordEntity> getKeysByPerson(PersonEntity id) { return service.getKeysByPerson(id);}

    //Запрос: GET keyword/ выводит все ключевые слова
    @RequestMapping(path = "/keyword", method = GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<KeywordEntity> getAllKeys() { return service.getAllKeys();  }

    // добавляет имя методом POST
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addKeyword(@RequestParam String name, Long id) { service.saveKeyword(name, id);}

}
