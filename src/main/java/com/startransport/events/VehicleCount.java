package com.startransport.events;

import com.startransport.entities.VehicleType;

public class VehicleCount extends Event {

    private String passengerId;
    private VehicleType vehicleType;
    private String vehicleID;

    public VehicleCount(String eventId, String passengerId, VehicleType vehicleType, String vehicleID) {
        super(eventId);
        this.passengerId = passengerId;
        this.vehicleType = vehicleType;
        this.vehicleID = vehicleID;
    }

//    public VehicleCount(String eventId) {
//        super(eventId);
//    }
//
//    public String getPassengerId() {
//        return passengerId;
//    }
//
//    public void setPassengerId(String passengerId) {
//        this.passengerId = passengerId;
//    }
//
//    public VehicleType getVehicleType() {
//        return vehicleType;
//    }
//
//    public void setVehicleType(VehicleType vehicleType) {
//        this.vehicleType = vehicleType;
//    }
//
//    public String getVehicleID() {
//        return vehicleID;
//    }
//
//    public void setVehicleID(String vehicleID) {
//        this.vehicleID = vehicleID;
//    }
}
