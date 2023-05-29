package com.startransport.events;

public class VehiclePassedStop extends Event {
    String passengerID;

    String EventID;
    String EventType;
    String VehicleType;
    String VehicleID;

    public VehiclePassedStop(String eventId,String eventType,String vehicleType,String vehicleID) {

        super(eventId);
        this.EventID = eventId;
        this.EventType = eventType;
        this.VehicleType = vehicleType;
        this.VehicleID = vehicleID;

    }
    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    public String getEventID() {
        return EventID;
    }

    public void setEventID(String eventID) {
        EventID = eventID;
    }

    public String getEventType() {
        return EventType;
    }

    public void setEventType(String eventType) {
        EventType = eventType;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public String getVehicleID() {
        return VehicleID;
    }

    public void setVehicleID(String vehicleID) {
        VehicleID = vehicleID;
    }
}
