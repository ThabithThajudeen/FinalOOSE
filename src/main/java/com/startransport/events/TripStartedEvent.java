//package com.startransport.events;

//public class TripStartedEvent extends Event {
//
//    private String passengerId;
//    private VehicleType vehicleType;
//    private String vehicleID;
//
//
//    public TripStartedEvent(String eventId, String passengerId, VehicleType vehicleType, String vehicleID) {
//        super(eventId);
//        this.passengerId = passengerId;
//        this.vehicleType = vehicleType;
//        this.vehicleID = vehicleID;
//    }
//
//    public String getVehicleID() {
//        return vehicleID;
//    }
//
//    public void setVehicleID(String vehicleID) {
//        this.vehicleID = vehicleID;
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
//
//}
//
package com.startransport.events;

public class TripStartedEvent extends Event {

    private String passengerId;
    private VehicleType vehicleType;
    private String vehicleID;
    private int startStopCount;

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
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
    public int getStartStopCount() {
        return startStopCount;
    }

    public void setStartStopCount(int startStopCount) {
        this.startStopCount = startStopCount;
    }

    public TripStartedEvent(String eventId, String passengerId, VehicleType vehicleType, String vehicleID) {
        super(eventId);
        this.passengerId = passengerId;
        this.vehicleType = vehicleType;
        this.vehicleID = vehicleID;
        this.startStopCount = startStopCount;
    }

    // ... existing getters and setters ...



}
