package org.example;

import java.time.LocalDateTime;
public class Trips{
    public Trips(int passengerId, String tripId, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, String vehicleId, String vehicleStartLocation, String vehicleEndLocation) {
        this.passengerId = passengerId;
        this.tripId = tripId;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.vehicleId = vehicleId;
        this.vehicleStartLocation = vehicleStartLocation;
        this.vehicleEndLocation = vehicleEndLocation;
    }

    private int passengerId;
    private String tripId;
    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;
    private String vehicleId;
    //  private String accountStatus;
    private String vehicleStartLocation;
    private String vehicleEndLocation;

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public LocalDateTime getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleStartLocation() {
        return vehicleStartLocation;
    }

    public void setVehicleStartLocation(String vehicleStartLocation) {
        this.vehicleStartLocation = vehicleStartLocation;
    }

    public String getVehicleEndLocation() {
        return vehicleEndLocation;
    }

    public void setVehicleEndLocation(String vehicleEndLocation) {
        this.vehicleEndLocation = vehicleEndLocation;
    }



    // Constructor

}


