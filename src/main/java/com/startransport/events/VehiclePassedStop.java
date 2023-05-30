package com.startransport.events;

public class VehiclePassedStop extends Event {
    String passengerID;
    String eventID;
    String eventType;
    String vehicleType;
    String vehicleID;

    public VehiclePassedStop(String eventId, String eventType, String vehicleType, String vehicleID) {

        super(eventId);
        this.eventID = eventId;
        this.eventType = eventType;
        this.vehicleType = vehicleType;
        this.vehicleID = vehicleID;

    }

    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }
}
