package ru.lightapps.telemetry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lightapps.telemetry.model.Weather;
import ru.lightapps.telemetry.repository.WeatherRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class WeatherService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WeatherRepository repository;

    public Weather getLast() {
        return repository.findTopByOrderByDateTimeDesc();
    }

    public Weather create(Weather weather) {
        log.info("create weather with data: " + LocalDateTime.now(ZoneId.of("Asia/Novosibirsk")) + " weather =" + weather);
        weather.setDateTime(LocalDateTime.now(ZoneId.of("Asia/Novosibirsk")));
        return repository.save(weather);
    }

    public List<Weather> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
        return repository.getBetween(startDateTime, endDateTime).orElseThrow(() -> new IllegalArgumentException("not found measures"));
    }

}
