package org.lenteam.colmen.controllers;

import org.lenteam.colmen.services.DefaultService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Anton_Solovev
 * @since 8/20/2016
 *
 * this class will be deleted
 */
@RestController
@RequestMapping("")
public class DefaultRestController {
    DefaultService service;
    public DefaultRestController(DefaultService service) {
        this.service = service;
    }

    //@RequestMapping(path = "/{name}", method = POST)
    //public void addDefaultPerson(@RequestParam(defaultValue = "King Kong") String name) { service.saveWithName(name);}

}
