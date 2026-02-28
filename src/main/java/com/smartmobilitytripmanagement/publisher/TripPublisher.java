package com.smartmobilitytripmanagement.publisher;

import com.smartmobilitytripmanagement.config.RabbitMQConfig;
import com.smartmobilitytripmanagement.dto.TripCompletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TripPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishTripCompleted(TripCompletedEvent tripEvent) {
        log.info("Envoi d'un événement de trajet complété à RabbitMQ (ID: {})", tripEvent.getTripId());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.COMPLETED_ROUTING_KEY, tripEvent);
    }
}
