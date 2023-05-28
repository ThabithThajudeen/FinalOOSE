package com.startransport.events;

import com.startransport.Commuter;

import java.time.LocalDateTime;

public class TripStartedEvent extends Event {


    public TripStartedEvent(String eventId, EventType eventType, String entityId, LocalDateTime eventTime, LocalDateTime paymentRequiredTime, LocalDateTime paymentMadeTime, LocalDateTime scheduledArrivalTime, Commuter commuter) {
        super(eventId, eventType, entityId, eventTime, paymentRequiredTime, paymentMadeTime, scheduledArrivalTime, commuter);


    }

    @Override
    void update(Event event) {
        if (event instanceof TripStartedEvent) {
            System.out.println("Trip has started.");

        }
    }
}
