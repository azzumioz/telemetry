package ru.lightapps.telemetry.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lightapps.telemetry.model.Measure;
import ru.lightapps.telemetry.model.User;
import ru.lightapps.telemetry.service.MeasureSearchValue;
import ru.lightapps.telemetry.service.MeasureService;
import ru.lightapps.telemetry.to.MeasureTo;
import ru.lightapps.telemetry.util.exception.ErrorInfo;
import ru.lightapps.telemetry.util.exception.MeasureUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@CrossOrigin(origins = "*")
public class MeasureController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MeasureService service;

    @GetMapping("/rest")
    @ResponseBody
    public List<Measure> getAll() {
        log.info("get all measures");
        return service.getAll();
    }

    @PostMapping("/rest/page")
    public ResponseEntity<Page<Measure>> getPage(@RequestBody MeasureSearchValue measureSearchValue) {
        log.info("get page measures");

        String sortColumn = measureSearchValue.getSortColumn();
        String sortDirection = measureSearchValue.getSortDirection();
        Integer pageNumber = measureSearchValue.getPageNumber();
        Integer pageSize = measureSearchValue.getPageSize();
        Sort.Direction direction = sortDirection == null || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortColumn);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<Measure> result = service.getPage(pageRequest);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/rest/today")
    @ResponseBody
    public List<Measure> getAllToday(Model model) {
        log.info("get today measures");
        return service.getAllToday();
    }

    @GetMapping("/rest/last")
    @ResponseBody
    public Measure getLast(Model model) {
        log.info("get last measures");
        return service.getLast();
    }

    @GetMapping("/rest/lastto")
    @ResponseBody
    public MeasureTo getLastWithOut(Model model) {
        log.info("get last measures with weather");
        Measure measure = service.getLast();
        MeasureTo measureTo = MeasureUtil.asTo(measure);
        return measureTo;
    }

    @GetMapping("/rest/filter")
    @ResponseBody
    public List<Measure> getBetween(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) LocalTime startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) LocalTime endTime) {
        log.info("get measures by filter: startDate = " + startDate + " endDate = " + endDate);
        return service.getBetween(startDate, startTime, endDate, endTime);
    }

    @GetMapping("/validateLogin")
    @ResponseBody
    public User validateLogin() {
        return new User("User successfully authenticated");
    }

    @GetMapping("/rest/{id}")
    @ResponseBody
    public Measure get(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping()
    public ResponseEntity<Measure> createMeasure(@Valid @RequestBody Measure measure) {
        log.info("post measure");
        Measure created = service.create(measure);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/last")
    public String last(Model model) {
        Measure measure = service.getLast();
        model.addAttribute("measure", measure);
        return "last";
    }

    @GetMapping("/measures")
    public String getAllMeasures(Model model) {
        List<Measure> measures = service.getAll();
        model.addAttribute("measures", measures);
        return "measures";
    }

    @GetMapping("/today")
    public String getTodayMeasures(Model model) {
        List<Measure> measures = service.getAllToday();
        model.addAttribute("measures", measures);
        return "today";
    }

    @GetMapping("/graph")
    public String showGraph(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.M.yyyy HH:mm");
        List<Measure> measures = service.getLast20();
        Map<String, Double> tempMap = new LinkedHashMap<>();
        Map<String, Double> humMap = new LinkedHashMap<>();
        Map<String, Integer> co2Map = new LinkedHashMap<>();
        for (Measure measure : measures) {
            tempMap.put(measure.getDateTime().format(formatter), measure.getTemperature());
            humMap.put(measure.getDateTime().format(formatter), measure.getHumidity());
            co2Map.put(measure.getDateTime().format(formatter), measure.getCo2());
        }
        model.addAttribute("tempMap", tempMap);
        model.addAttribute("humMap", humMap);
        model.addAttribute("co2Map", co2Map);
        return "graph";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorInfo illegalArgument(HttpServletRequest req, IllegalArgumentException e) {
        return new ErrorInfo(req.getRequestURL(), e.getLocalizedMessage());
    }

}
