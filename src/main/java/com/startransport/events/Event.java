package com.startransport.events;

import com.startransport.Commuter;
import com.startransport.NotBoardState;
import com.startransport.OnboardState;

import java.time.LocalDateTime;

public abstract class Event {
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }




    private String eventId;

    public Event(String eventId, LocalDateTime eventTime) {
        this.eventId = eventId;
        this.eventTime = eventTime;
    }

    private LocalDateTime eventTime;






    abstract void update(Event event);

    // Getters and setters
    //...
}