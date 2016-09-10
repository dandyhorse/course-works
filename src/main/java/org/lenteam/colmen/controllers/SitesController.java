package org.lenteam.colmen.controllers;

import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.models.Site;
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
@RequestMapping("/sites")
public class SitesController {

    @Autowired
    private CommonUserService userService;
    @Autowired
    private CommonAdminService adminService;

    public SitesController(CommonUserService userService, CommonAdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    // получает список всех сайтов Запрос: GET /sites
    @RequestMapping(method = GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<Site> getAll() {
        return userService.getAllSites();
    }

    // добавляет сайт методом POST
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSite(@RequestBody ModelMap model) {
        String name = (String) model.get("name");
        adminService.saveSite(name);
    }

    // удаляет сайт методом DEELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteSite(@PathVariable Long id) {
        adminService.deleteSite(id);
    }
}
