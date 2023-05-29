package com.startransport.events;

import com.startransport.Commuter;
import com.startransport.NotBoardState;
import com.startransport.OnboardState;

import java.time.LocalDateTime;

public abstract class Event {
    private LocalDateTime eventTime;
    private String eventId;
    public Event(String eventId) {
        this.eventId = eventId;
        this.eventTime = LocalDateTime.now();
    }
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

}