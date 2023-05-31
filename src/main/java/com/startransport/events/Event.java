package com.startransport.events;

import java.time.LocalDateTime;

public class Event {
    private LocalDateTime eventTime;
   private String eventId;
    public Event(String eventId) {
        this.eventId = eventId;
        this.eventTime = LocalDateTime.now();
    }



}