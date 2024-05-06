package ru.lightapps.telemetry.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.lightapps.telemetry.model.Weather;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class JsonParserService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public Weather getWeatherFromJson(String json) {
        Weather weather = new Weather();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(json);

            JsonNode nameNode = rootNode.path("name");
            String name = nameNode.asText();

            JsonNode mainNode = rootNode.path("main");
            JsonNode weatherNode = rootNode.path("weather");

            JsonNode temperatureNode = mainNode.path("temp");
            double temperature = temperatureNode.asDouble();

            JsonNode pressureNode = mainNode.path("pressure");
            int pressure = pressureNode.asInt();

            JsonNode humidityNode = mainNode.path("humidity");
            double humidity = humidityNode.asDouble();

            JsonNode rainNode = rootNode.findPath("1h");
            double rain = rainNode.asDouble();

            JsonNode descriptionNode = rootNode.findPath("description");
            String description = descriptionNode.asText();

            JsonNode iconNode = rootNode.findPath("icon");
            String icon = iconNode.asText();

            JsonNode windNode = rootNode.findPath("wind");
            JsonNode speedNode = windNode.path("speed");
            double windSpeed = speedNode.asDouble();
            JsonNode degNode = windNode.path("deg");
            int windDeg = degNode.asInt();

            JsonNode cloudsNode = rootNode.findPath("all");
            int clouds = cloudsNode.asInt();

            JsonNode sunriseNode = rootNode.findPath("sunrise");
            long sunrise = sunriseNode.asLong();

            JsonNode sunsetNode = rootNode.findPath("sunset");
            long sunset = sunsetNode.asLong();

            weather.setDateTime(LocalDateTime.now(ZoneId.of("Asia/Novosibirsk")));
            weather.setName(name);
            weather.setTemperature(new BigDecimal(temperature).setScale(1, RoundingMode.UP).doubleValue());
            weather.setPressure(pressure);
            weather.setHumidity(humidity);
            weather.setDescription(description);
            weather.setIcon(icon);
            weather.setRain(rain);
            weather.setClouds(clouds);
            weather.setWind_speed(windSpeed);
            weather.setWind_deg(windDeg);
            weather.setSunrise(LocalDateTime.ofEpochSecond(sunrise, 0, ZoneOffset.of("+07:00:00")));
            weather.setSunset(LocalDateTime.ofEpochSecond(sunset, 0, ZoneOffset.of("+07:00:00")));

        } catch (IOException e) {
            log.error("Exception is throwed : " + e);
        }

        return weather;
    }

}
