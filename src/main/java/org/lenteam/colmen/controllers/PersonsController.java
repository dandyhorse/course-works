package org.lenteam.colmen.controllers;

import org.lenteam.colmen.models.Person;
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

    public PersonsController(CommonUserService service, CommonAdminService adminService) {
        this.service = service;
        this.adminService = adminService;
    }

    //Запрос: GET /persons выводит весь список
    @RequestMapping(method = GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.FOUND)
    public Iterable<Person> getAllPersons() {
        return service.getAllPersons();
    }

    // добавляет имя методом POST
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPerson(@RequestBody ModelMap model) {
        String name = (String) model.get("name");
        adminService.savePerson(name);
    }

    // удаляет персону методом DEELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable Long id) {
        adminService.deletePerson(id);
    }

}
