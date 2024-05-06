package ru.lightapps.telemetry.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather")
public class Weather {

    private static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "temperature", nullable = false)
    private double temperature;

    @Column(name = "pressure", nullable = false)
    private int pressure;

    @Column(name = "humidity", nullable = false)
    private double humidity;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "icon", nullable = false)
    private String icon;

    @Column(name = "rain", nullable = false)
    private double rain;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "clouds", nullable = false)
    private int clouds;

    @Column(name = "wind_speed", nullable = false)
    private double wind_speed;

    @Column(name = "wind_deg", nullable = false)
    private int wind_deg;

    @Column(name = "sunrise", nullable = false)
    private LocalDateTime sunrise;

    @Column(name = "sunset", nullable = false)
    private LocalDateTime sunset;

    public Weather() {
    }

    public Weather(String name, double temperature, int pressure, double humidity, LocalDateTime dateTime, String icon, double rain, String description, int clouds, double wind_speed, int wind_deg, LocalDateTime sunrise, LocalDateTime sunset) {
        this.name = name;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.dateTime = dateTime;
        this.icon = icon;
        this.rain = rain;
        this.description = description;
        this.clouds = clouds;
        this.wind_speed = wind_speed;
        this.wind_deg = wind_deg;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public int getWind_deg() {
        return wind_deg;
    }

    public void setWind_deg(int wind_deg) {
        this.wind_deg = wind_deg;
    }

    public LocalDateTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalDateTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalDateTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalDateTime sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", dateTime=" + dateTime +
                ", icon='" + icon + '\'' +
                ", rain=" + rain +
                ", description='" + description + '\'' +
                ", clouds=" + clouds +
                ", wind_speed=" + wind_speed +
                ", wind_deg=" + wind_deg +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }


}
