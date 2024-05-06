package ru.lightapps.telemetry.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.lightapps.telemetry.model.Weather;
import ru.lightapps.telemetry.service.WeatherService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class WeatherController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WeatherService service;

    @GetMapping("/rest/weatherlast")
    @ResponseBody
    public Weather getLast(Model model) {
        log.info("get last weather");
        return service.getLast();
    }

    @GetMapping("/rest/weatherfilter")
    @ResponseBody
    public List<Weather> getBetween(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) LocalTime startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) LocalTime endTime) {
        log.info("get weather by filter: startDate = " + startDate + " endDate = " + endDate);
        return service.getBetween(startDate, startTime, endDate, endTime);
    }

}
