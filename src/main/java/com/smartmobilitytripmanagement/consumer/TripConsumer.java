package com.smartmobilitytripmanagement.consumer;

import com.smartmobilitytripmanagement.beans.Trip;
import com.smartmobilitytripmanagement.config.RabbitMQConfig;
import com.smartmobilitytripmanagement.dto.TripPricedEvent;
import com.smartmobilitytripmanagement.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TripConsumer {

    @Autowired
    private TripRepository tripRepository;

    @RabbitListener(queues = RabbitMQConfig.PRICED_QUEUE)
    public void receivePricedEvent(TripPricedEvent pricedEvent) {
        log.info("Réception d'une mise à jour de prix pour le trajet ID: {}", pricedEvent.getTripId());

        Optional<Trip> tripOpt = tripRepository.findById(pricedEvent.getTripId());
        if (tripOpt.isPresent()) {
            Trip trip = tripOpt.get();
            // TODO: Gérer le montant final (le champ calculé a été supprimé car géré par le
            // service Pricing)
            // trip.setPrice(pricedEvent.getFinalAmount().doubleValue());
            tripRepository.save(trip);
            log.info("Prix mis à jour avec succès : {}", pricedEvent.getFinalAmount());
        } else {
            log.warn("Trajet ID: {} non trouvé en base", pricedEvent.getTripId());
        }
    }
}
