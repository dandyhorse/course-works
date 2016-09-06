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
// получает список всех сайтов Запрос: GET /sites
    @Autowired
    private CommonUserService userService;

    public SitesController (CommonUserService userService) { this.userService = userService; }

    @RequestMapping(path = "", method = GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<SiteEntity> getAll(){ return userService.getAllSites();}

    // добавляет сайт методом POST
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addSite(@RequestParam(defaultValue = "mail.ru") String name) { userService.saveWithName(name);}
}
