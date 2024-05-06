package ru.lightapps.telemetry.to;

import java.time.LocalDateTime;

public class MeasureTo {

    private double temperature;
    private double temperatureOut;
    private int co2;
    private double humidity;
    private double humidityOut;
    private LocalDateTime dateTime;

    public MeasureTo(double temperature, int co2, double humidity, LocalDateTime dateTime) {
        this.temperature = temperature;
        this.co2 = co2;
        this.humidity = humidity;
        this.dateTime = dateTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemperatureOut() {
        return temperatureOut;
    }

    public void setTemperatureOut(double temperatureOut) {
        this.temperatureOut = temperatureOut;
    }

    public double getHumidityOut() {
        return humidityOut;
    }

    public void setHumidityOut(double humidityOut) {
        this.humidityOut = humidityOut;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
