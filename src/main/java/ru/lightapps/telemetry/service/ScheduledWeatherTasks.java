package ru.lightapps.telemetry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.lightapps.telemetry.model.Weather;
import ru.lightapps.telemetry.util.exception.NotConnectionException;

@Component
public class ScheduledWeatherTasks {

    @Autowired
    WeatherService weatherService;

    @Autowired
    private JsonParserService jsonParserService;

    @Value("${openweathermap.api.url}")
    private String weatherUrl;

    @Value("${openweathermap.api.location}")
    private String location;

    @Value("${openweathermap.api.key}")
    private String key;

    @Value("${openweathermap.api.units}")
    private String units;

    @Scheduled(initialDelayString = "${scheduler.initialDelayString}", fixedDelayString = "${scheduler.fixedDelayString}")
    public void reportCurrentTime() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String weatherStr = restTemplate.getForObject(weatherUrl, String.class, location, key, units);
            weatherService.create(getWeatherFromJson(weatherStr));
        } catch (Exception e) {
            throw new NotConnectionException("not connection to " + weatherUrl);
        }
    }

    private Weather getWeatherFromJson(String json) {
        return jsonParserService.getWeatherFromJson(json);
    }

}
