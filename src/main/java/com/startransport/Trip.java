package com.startransport;

import java.time.LocalDateTime;
public class Trip {
    public Trip(int passengerId, String tripId, LocalDateTime timeStart, LocalDateTime timeEnd, String vehicleId, String vehicleStartLocation, String vehicleEndLocation) {
        this.passengerId = passengerId;
        this.tripId = tripId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.vehicleId = vehicleId;
     //   this.vehicleStartLocation = vehicleStartLocation;
     //   this.vehicleEndLocation = vehicleEndLocation;
    }

    private int passengerId;
    private String tripId;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private String vehicleId;
    //  private String accountStatus;
    private String vehicleStartLocation;
    private String vehicleEndLocation;
    private boolean isOngoing;

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    private boolean isPaid;

    public boolean isOngoing() {
        return isOngoing;
    }

    public void setOngoing(boolean ongoing) {
        isOngoing = ongoing;
    }



    public double getCurrentFair() {
        return currentFair;
    }

    public void setCurrentFair(double currentFair) {
        this.currentFair = currentFair;
    }

    private double currentFair; // -> If the trip is ongoing this represents the trip fair upto the current bus stop.

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

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
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


