package org.lenteam.colmen.controllers;

import org.lenteam.colmen.entites.PersonEntity;
import org.lenteam.colmen.services.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Anton_Solovev
 * @since 8/20/2016
 */
@RestController
@RequestMapping("/default")
public class DefaultRestController {

    private DefaultService service;

    public DefaultRestController(DefaultService service) {
        this.service = service;
    }

    @RequestMapping(path = "/{id}", method = GET)
    public PersonEntity getDefaultPerson(@PathVariable Long id) {
        return service.findOne(id);
    }

    @RequestMapping(path = "/{name}", method = POST)
    public void addDefaultPerson(@RequestParam(defaultValue = "King Kong")
                                         String name) {
        service.saveWithName(name);
    }

}
