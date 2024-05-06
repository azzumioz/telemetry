package ru.lightapps.telemetry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.lightapps.telemetry.model.Measure;
import ru.lightapps.telemetry.repository.MeasureRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class MeasureService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MeasureRepository repository;

    public Measure get(int id) {
        return repository.findById(id);
    }

    public List<Measure> getAll() {
        return repository.findAllByOrderByDateTime();
    }

    public Page<Measure> getPage(PageRequest paging) {
        return repository.findAll(paging);
    }

    public List<Measure> getLast20() {
        return repository.findAllByOrderByDateTime();
    }

    public Measure getLast() {
        return repository.findTopByOrderByDateTimeDesc();
    }

    public Measure getLastOut() {
        return repository.findTopByOrderByDateTimeDesc();
    }

    public List<Measure> getAllToday() {
        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return repository.getBetween(startDateTime, endDateTime).orElseThrow(() -> new IllegalArgumentException("not found measures"));
    }

    public List<Measure> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
        return repository.getBetween(startDateTime, endDateTime).orElseThrow(() -> new IllegalArgumentException("not found measures"));
    }

    public Measure create(Measure measure) {
        log.info("create measure data = {},  t = {}, hum = {}, CO2 = {}", measure.getDateTime(), measure.getTemperature(), measure.getHumidity(), measure.getCo2());
        return repository.save(measure);
    }


}
