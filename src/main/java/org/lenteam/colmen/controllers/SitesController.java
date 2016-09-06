package org.lenteam.colmen.controllers;

import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.services.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Mickey on 05.09.2016.
 */

@RestController
@RequestMapping("/sites")
public class SitesController {

    @Autowired
    private CommonUserService userService;

    public SitesController (CommonUserService userService) { this.userService = userService; }

    // получает список всех сайтов Запрос: GET /sites
    @RequestMapping(method = GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<SiteEntity> getAll(){ return userService.getAllSites();}

    // добавляет сайт методом POST
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addSite(@RequestParam(defaultValue = "mail.ru") String name) { userService.saveSite(name);}

    // удаляет сайт методом DEELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSite(@PathVariable("id") Long id) {userService.deleteSite(id);}
}
