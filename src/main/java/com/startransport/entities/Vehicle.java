package com.startransport.entities;

import com.startransport.observers.VehicleObserver;

import java.util.ArrayList;

public class Vehicle {
    private int availableSeatCount;
    private String vehicleID;
    private int totalSeatCount;
    private VehicleType vehicleType;
    private int currentStopCount;

    public int getCurrentStopCount() {
        return currentStopCount;
    }
    public void incrementStopCount() {
        this.currentStopCount++;
    }

    public void setCurrentStopCount(int currentStopCount) {
        this.currentStopCount = currentStopCount;
    }


    private ArrayList<VehicleObserver> observers = new ArrayList<>();

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }



    public int getTotalSeatCount() {
        return totalSeatCount;
    }

    public void setTotalSeatCount(int totalSeatCount) {
        this.totalSeatCount = totalSeatCount;
    }



    public Vehicle(String vehicleID, int availableSeatCount, int totalSeatCount) {
        this.availableSeatCount = availableSeatCount;
        this.vehicleID = vehicleID;
        this.totalSeatCount = totalSeatCount;
    }



    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }



    public Vehicle(int availableSeatCount) {
        this.availableSeatCount = availableSeatCount;
    }

    public boolean hasAvailableSeats() {
        return availableSeatCount > 0;
    }

    public void decrementSeatCount() {
        if(availableSeatCount > 0) {
            availableSeatCount--;
        }
    }

    public void incrementSeatCount() {
        availableSeatCount++;
    }
    public void attachObserver(VehicleObserver observer) {
        this.observers.add(observer);
    }

    public void notifyAllTripObservers() {
        for (VehicleObserver o : observers) {
            o.updateCurrentVehicle();

        }
    }
}


