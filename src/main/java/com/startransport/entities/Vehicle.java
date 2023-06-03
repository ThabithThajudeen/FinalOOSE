package com.startransport.entities;
import com.startransport.observers.Observer;
import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private int availableSeatCount;
    private String vehicleID;
    private int totalSeatCount;
    private VehicleType vehicleType;
    private int currentStopCount;
    public Vehicle(String vehicleID, int availableSeatCount, int totalSeatCount,VehicleType vehicleType) {
        this.availableSeatCount = availableSeatCount;
        this.vehicleID = vehicleID;
        this.totalSeatCount = totalSeatCount;
        this.vehicleType = vehicleType;
    }

    public int getCurrentStopCount() {
        return currentStopCount;
    }
    public void incrementStopCount() {
        this.currentStopCount++;
    }
    private List<Observer<Vehicle>> vehicleObservers = new ArrayList<>();

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getTotalSeatCount() {
        return totalSeatCount;
    }

    public String getVehicleID() {
        return vehicleID;
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

    public int getOngoingTripCount(){
        return vehicleObservers.size();
    }
    public void attachObserver(Observer<Vehicle> observer) {
        this.vehicleObservers.add(observer);
    }

}


