package com.startransport.events;

public class VehicleLeft extends Event {

    private VehicleType vehicleType;

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }


    public VehicleLeft(String eventId,VehicleType vehicleType) {
        super(eventId);
        this.vehicleType = vehicleType;
    }
}

