package com.sagant.events.activemq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de producir eventos, utilizando JMS.
 */
@Component
public class Producer {

    private final JmsTemplate jmsTemplate;
    @Value("${spring.artemis.embedded.queues}")
    private String queueName;

    public Producer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /***
     * Genera un nuevo evento con la especificación pasada por parámetro.
     * Lo envía a la cola de eventos para que se atienda desde otro componente de forma asincrona.
     * @param event especificación del nuevo evento.
     */
    public void generateEvent(Event event) {
        jmsTemplate.convertAndSend(queueName, event);
    };
}
