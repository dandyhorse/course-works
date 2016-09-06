package org.lenteam.colmen.controllers;

import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.SiteEntity;
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

@RestController

public class UserController {

    @Autowired
    CommonUserService service;



}
