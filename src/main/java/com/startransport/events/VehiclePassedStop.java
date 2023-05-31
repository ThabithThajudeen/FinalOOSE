package com.startransport.events;

import com.startransport.entities.VehicleType;

public class VehiclePassedStop extends Event {
    private String passengerID;
    private String eventID;
    private String eventType;
    private VehicleType vehicleType;
    private String vehicleID;

    public VehiclePassedStop(String eventId, String eventType, VehicleType vehicleType, String vehicleID) {

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

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }
}
