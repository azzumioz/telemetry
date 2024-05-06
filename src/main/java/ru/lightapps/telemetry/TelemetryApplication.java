package ru.lightapps.telemetry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TelemetryApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TelemetryApplication.class, args);
        System.out.println("Server running");
    }

}
