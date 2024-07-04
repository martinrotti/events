package com.sagant.events.services;

import com.sagant.events.activemq.Producer;
import com.sagant.events.activemq.Event;
import org.springframework.stereotype.Service;

/**
 * Service para el manejo de eventos que se generan en la aplicación.
 */
@Service
public class EventService {

    private final Producer producer;

    public EventService(Producer producer) {
        this.producer = producer;
    }

    /***
     * Convoca al productor de eventos para que genere uno nuevo.
     * @param event especificación del nuevo evento.
     */
    public void generateEvent(Event event) {
        producer.generateEvent(event);
    }
}
