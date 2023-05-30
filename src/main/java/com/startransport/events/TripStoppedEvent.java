package com.startransport.events;

public class TripStoppedEvent extends Event {
    private String passengerId;

    public TripStoppedEvent(String eventId, String passengerId) {
        super(eventId);
        this.passengerId = passengerId;

    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }


}
