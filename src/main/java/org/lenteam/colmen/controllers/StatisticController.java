package org.lenteam.colmen.controllers;

import org.lenteam.colmen.models.DailyStatistic;
import org.lenteam.colmen.models.StatisticPerson;
import org.lenteam.colmen.services.interfaces.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Anton_Solovev
 * @author Mickey
 * @since 9/9/2016.
 */
@RestController
@RequestMapping(path = "/stat")
public class StatisticController {

    @Autowired
    private CommonUserService service;

    public StatisticController(CommonUserService service) {
        this.service = service;
    }

    //Запрос: GET stat/persons/onsite/id,  выводит список имен по конкретному сайту. id - id сайта
    @RequestMapping(path = "/persons/onsite/{id}", method = GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.FOUND)
    public Iterable<StatisticPerson> getPersonsOnSite(@PathVariable Long id) {
        return service.getPersonsOnSite(id);
    }

    //Запрос: GET /stat/daily/{person_id}/{site_id}
    // получить ежедневную статистику
    @RequestMapping(path = "/daily/{person_id}/{site_id}", method = GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.FOUND)
    public DailyStatistic getPersonsOnSite(@PathVariable Long person_id, @PathVariable Long site_id) {
        return service.getPersonStatisticOnSite(person_id, site_id);
    }

}
