package com.sagant.events.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de procesar los eventos que se generan, utilizando JMS.
 */
@Component
public class Consumer {

    /***
     * Cada vez que se genera un evento en la cola de destino, el mismo es atendido en esta función.
     * @param event especificación del evento que tiene que ser atendido.
     */
    @JmsListener(destination = "${spring.artemis.embedded.queues}")
    public void consume(Event event) {
        System.out.println("Received Event: " + event);
    }
}
