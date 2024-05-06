package ru.lightapps.telemetry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.lightapps.telemetry.model.Measure;
import ru.lightapps.telemetry.util.exception.NotConnectionException;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class ScheduledMeasureTasks {

    @Value("${measurestation.url}")
    private String baseUrl;

    @Autowired
    MeasureService measureService;

    public void reportCurrentTime() {
        RestTemplate restTemplate = new RestTemplate();

        try {
            Measure measure = restTemplate.getForObject(baseUrl, Measure.class);
            measureService.create(measure);
        } catch (Exception e) {
            measureService.create(new Measure(0.0, 0, 0.0, LocalDateTime.now(ZoneId.of("Asia/Novosibirsk"))));
            throw new NotConnectionException("not connection to " + baseUrl);
        }

    }

}
