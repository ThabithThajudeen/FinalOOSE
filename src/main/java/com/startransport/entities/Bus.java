package com.startransport.entities;

import com.startransport.observers.VehicleObserver;

import java.util.ArrayList;

public class Bus {
    private String passengerID;
    private String busID;
    private ArrayList<VehicleObserver> observers = new ArrayList<>();
    private int initialStopCount;
    private int currentStopCount;
    private int tripPassedCount;
    public int getTripPassedCount() {
        return tripPassedCount;
    }

    public void setTripPassedCount(int tripPassedCount) {
        this.tripPassedCount = tripPassedCount;
    }
    public void incrementStopCount() {
        this.currentStopCount++;
    }

    public ArrayList<VehicleObserver> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<VehicleObserver> observers) {
        this.observers = observers;
    }

    public int getInitialStopCount() {
        return initialStopCount;
    }

    public void setInitialStopCount(int initialStopCount) {
        this.initialStopCount = initialStopCount;
    }

    public int getCurrentStopCount() {
        return currentStopCount;
    }

    public void setCurrentStopCount(int currentStopCount) {
        this.currentStopCount = currentStopCount;
    }


    public Bus(String passengerID, String vehicleID) {
        this.passengerID = passengerID;
        this.busID = vehicleID;

    }


    public Bus(String vehicleID) {
        this.busID = vehicleID;
    }

    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public void attachObserver(VehicleObserver observer) {
        this.observers.add(observer);
    }

    public void notifyAllTripObservers() {
        for (VehicleObserver o : observers) {
            o.updateCurrentstand();

        }
    }
    public int getStopsPassed() {
        return currentStopCount - initialStopCount;
    }

}