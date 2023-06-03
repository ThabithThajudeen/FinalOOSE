package com.startransport.events;

import java.time.LocalDateTime;

public class Event {
    private LocalDateTime eventTime;
    private String eventId;
    public Event(String eventId) {
        this.eventId = eventId;
        this.eventTime = LocalDateTime.now();
    }
    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }





}