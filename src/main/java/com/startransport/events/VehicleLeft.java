package com.startransport.events;

import com.startransport.entities.VehicleType;

public class VehicleLeft extends Event {

    private VehicleType vehicleType;
    private String vehicleID;

    public VehicleLeft(String eventId,VehicleType vehicleType) {
        super(eventId);
        this.vehicleType = vehicleType;
    }
    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }



    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }



}

