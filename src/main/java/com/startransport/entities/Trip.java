package com.startransport.entities;

import com.startransport.observers.TripObserver;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Trip {
    private String passengerId;
    private String tripId;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private String vehicleId;
    private ArrayList<TripObserver> observers = new ArrayList<>();


    private boolean isOngoing;
    public Trip(String tripId,String passengerId, String vehicleId) {

        this.tripId = tripId;
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;

    }



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

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
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
        this.notifyAllTripObservers();
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void attachObserver(TripObserver observer){
        this.observers.add(observer);
    }
    public void notifyAllTripObservers(){
        for (TripObserver o: observers){
            o.updateCurrentTrip();

        }
    }






    // Constructor

}


