package ru.lightapps.telemetry.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "measures")
public class Measure {

    private static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "temperature", nullable = false)
    private double temperature;

    @Column(name = "co2", nullable = false)
    private int co2;

    @Column(name = "humidity", nullable = false)
    private double humidity;

    @Column(name = "datetime", nullable = false)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateTime;

    public Measure() {
    }

    public Measure(Integer id, double temperature, int co2, double humidity, LocalDateTime dateTime) {
        this.id = id;
        this.temperature = temperature;
        this.co2 = co2;
        this.humidity = humidity;
        this.dateTime = dateTime;
    }

    public Measure(double temperature, int co2, double humidity, LocalDateTime dateTime) {
        this.temperature = temperature;
        this.co2 = co2;
        this.humidity = humidity;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Measure{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", co2=" + co2 +
                ", humidity=" + humidity +
                ", dateTime=" + dateTime +
                '}';
    }
}
