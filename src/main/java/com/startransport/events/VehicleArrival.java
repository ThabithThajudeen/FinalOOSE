package com.startransport.events;

import com.startransport.entities.VehicleType;

public class VehicleArrival extends Event{
    private VehicleType vehicleType;
    public VehicleArrival(String eventId,VehicleType vehicleType) {
        super(eventId);
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }



}
