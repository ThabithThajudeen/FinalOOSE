package com.startransport.entities;

import com.startransport.observers.Observer;
//import com.startransport.observers.VehicleObserver;

import java.util.ArrayList;
import java.util.List;

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

//    public void setCurrentStopCount(int currentStopCount) {
//        this.currentStopCount = currentStopCount;
//    }


    private List<Observer<Vehicle>> vehicleObservers = new ArrayList<>();

    public VehicleType getVehicleType() {
        return vehicleType;
    }

//    public void setVehicleType(VehicleType vehicleType) {
//        this.vehicleType = vehicleType;
//    }



    public int getTotalSeatCount() {
        return totalSeatCount;
    }

//    public void setTotalSeatCount(int totalSeatCount) {
//        this.totalSeatCount = totalSeatCount;
//    }



    public Vehicle(String vehicleID, int availableSeatCount, int totalSeatCount,VehicleType vehicleType) {
        this.availableSeatCount = availableSeatCount;
        this.vehicleID = vehicleID;
        this.totalSeatCount = totalSeatCount;
        this.vehicleType = vehicleType;
    }



    public String getVehicleID() {
        return vehicleID;
    }

//    public void setVehicleID(String vehicleID) {
//        this.vehicleID = vehicleID;
//    }



//    public Vehicle(int availableSeatCount) {
//        this.availableSeatCount = availableSeatCount;
//    }

    public boolean hasAvailableSeats() {
        return availableSeatCount > 0;
    }

    public void decrementSeatCount() {
        if(availableSeatCount > 0) {
            availableSeatCount--;
        }
    }

//    public void incrementSeatCount() {
//        availableSeatCount++;
//    }
//    public void attachObserver(VehicleObserver observer) {
//        this.vehicleObservers.add(observer);
//    }
//
//    public void notifyAllTripObservers() {
//        for (VehicleObserver o : vehicleObservers) {
//            o.updateCurrentVehicle(this);
//
//        }
//    }
    public int getOngoingTripCount(){
        return vehicleObservers.size();
    }
    public void attachObserver(Observer<Vehicle> observer) {
        this.vehicleObservers.add(observer);
    }

//    public void notifyAllTripObservers() {
//        for (Observer<Vehicle> o : vehicleObservers) {
//            o.update(this);
//
//        }
   // }
}


