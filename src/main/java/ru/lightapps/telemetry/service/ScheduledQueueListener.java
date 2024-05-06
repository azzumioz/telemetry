package ru.lightapps.telemetry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.lightapps.telemetry.model.Measure;

@Component
public class ScheduledQueueListener {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MeasureService measureService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(@Payload Measure measure) {
        log.info("Save: {} T = {}, Hum = {}, CO2 = {}", measure.getDateTime(), measure.getTemperature(), measure.getHumidity(), measure.getCo2());
        measureService.create(measure);
    }


}
