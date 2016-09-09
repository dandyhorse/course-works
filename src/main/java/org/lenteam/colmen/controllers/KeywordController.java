package org.lenteam.colmen.controllers;

import org.lenteam.colmen.entities.KeywordEntity;
import org.lenteam.colmen.services.interfaces.CommonAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Mickey
 * @since 06.09.2016.
 */

@RestController
@RequestMapping("/keywords")
public class KeywordController {

    @Autowired
    private CommonAdminService adminService;

    public KeywordController(CommonAdminService adminService) {
        this.adminService = adminService;
    }

    //Запрос: GET keyword/person_id,  person_id - идентификатор персоны, выводит список ключевых слов
    @RequestMapping(path = "/{id}", method = GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseStatus(HttpStatus.FOUND)
    public Iterable<KeywordEntity> getKeysByPerson(@PathVariable Long id) {
        return adminService.getKeysByPerson(id);
    }

    // добавляет имя методом POST
    @RequestMapping(value = "/{key}/{personId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addKeyword(@RequestBody ModelMap model) {
        String key = (String) model.get("key");
        Long personId = (Long) model.get("person_id");
        adminService.saveKeyword(key, personId);
    }

    // удаляет персону методом DEELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteKeyword(@PathVariable Long id) {
        adminService.deleteKeyword(id);
    }

}
