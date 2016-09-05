package org.lenteam.colmen.controllers;

import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.services.DefaultService;
import org.lenteam.colmen.services.SitesService;
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
@RequestMapping("/sites")
public class SitesController {
// получает список сайтов Запрос: GET /sites
    @Autowired
    private SitesService sitesService;

    public SitesController (SitesService sitesService) {
        this.sitesService = sitesService;
    }

    @RequestMapping(path = "", method = GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Hashtable<Long, SiteEntity> getAll(){ return sitesService.getAll();}
}
