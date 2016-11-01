package ru.ssau.tourism.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import ru.ssau.tourism.entities.Tour
import ru.ssau.tourism.services.DBService

@Controller
class TourController
@Autowired
constructor(private val service: DBService) {

    @RequestMapping("/all")
    @ResponseBody
    fun get(): Iterable<Tour> = service.tours

}
