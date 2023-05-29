package com.startransport.entities;

import com.startransport.observers.BusObserver;

import java.util.ArrayList;

public class Bus {
    private String passengerID;
    private String busID;
    private ArrayList<BusObserver> observers = new ArrayList<>();


    public Bus(String passengerID, String vehicleID) {
        this.passengerID = passengerID;
        this.busID = vehicleID;

    }


    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }


    public Bus(String vehicleID) {
        this.busID = vehicleID;
    }

    public String getBusID() {
        return busID;
    }
    public void setBusID(String busID) {
        this.busID = busID;
    }
    public void attachObserver(BusObserver observer){
        this.observers.add(observer);
    }
    public void notifyAllTripObservers(){
        for (BusObserver o: observers){
            o.updateCurrentBusStand();

        }
    }

}