package com.sagant.events.activemq;

import com.sagant.events.enums.EEntityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que contiene la información de un evento, incluyendo:
 * eventCode: código del evento
 * name: nombre del evento
 * date: fecha en que se genera
 * entityType: tipo de entidad
 * entityId: dentificador de la entidad
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event implements Serializable {
    private String eventCode;
    private String name;
    private LocalDate date;
    private EEntityType entityType;
    private Long entityId;
}
