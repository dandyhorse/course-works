package org.lenteam.colmen.controllers;

import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.services.interfaces.CommonAdminService;
import org.lenteam.colmen.services.interfaces.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Mickey
 * @since 05.09.2016.
 */
@RestController
@RequestMapping(path = "/persons")
public class PersonsController {

    @Autowired
    private CommonUserService service;
    @Autowired
    private CommonAdminService adminService;

    public PersonsController() {
    }

    public PersonsController(CommonUserService service) {
        this.service = service;
    }

    public PersonsController(CommonAdminService adminService) {
        this.adminService = adminService;
    }

    //Запрос: GET /persons выводит весь список
    @RequestMapping(method = GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseStatus(HttpStatus.FOUND)
    public Iterable<PersonEntity> getAllPersons() {
        return service.getAllPersons();
    }

    // добавляет имя методом POST
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addPerson(@RequestBody ModelMap model) {
        String name = (String) model.get("name");
        adminService.savePerson(name);
    }

    // удаляет персону методом DEELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long id) {
        adminService.deletePerson(id);
    }

}
