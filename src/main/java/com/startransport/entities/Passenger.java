package com.startransport.entities;
import com.startransport.*;
import com.startransport.observers.TripObserver;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Passenger implements TripObserver {
    private String passengerID;
    private String passengerName;
    private ArrayList<Trip> pastTrips = new ArrayList<>();
    private Trip currentTrip;
    private Bus currentBusStop;
    private Train currentTrainStop;
    private Bus currentBusCount;
    private Train currentTrainCount;

    public Passenger(String passengerID, String passengerName) {
        this.passengerID = passengerID;
        this.passengerName = passengerName;

    }
    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public ArrayList<Trip> getPastTrips() {
        return pastTrips;
    }

    public void setPastTrips(ArrayList<Trip> pastTrips) {
        this.pastTrips = pastTrips;
    }

    public Bus getCurrentBusCount() {
        return currentBusCount;
    }

    public void setCurrentBusCount(Bus currentBusCount) {
        this.currentBusCount = currentBusCount;
    }

    public Train getCurrentTrainCount() {
        return currentTrainCount;
    }

    public void setCurrentTrainCount(Train currentTrainCount) {
        this.currentTrainCount = currentTrainCount;
    }



    public Train getCurrentTrainStop() {
        return currentTrainStop;
    }

    public void setCurrentTrainStop(Train currentTrainStop) {
        this.currentTrainStop = currentTrainStop;
    }



    public Bus getCurrentBusStop() {
        return currentBusStop;
    }

    public void setCurrentBusStop(Bus currentBusStop) {
        this.currentBusStop = currentBusStop;
    }



    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    public Passenger(String passengerID) {
        this.passengerID = passengerID;

    }

    public Trip getCurrentTrip(){
        return currentTrip;
    }
    public void addToPastTrips(Trip trip){
        pastTrips.add(trip);
    }
    public ArrayList<Trip> getAllPastTrips(){
        ArrayList<Trip> x = new ArrayList<>();
        x.addAll(pastTrips);
        return x;
    }

    public void setCurrentTrip(Trip currentTrip) {
        this.currentTrip = currentTrip;
    }




    @Override
    public void updateCurrentTrip() {
        System.out.println("current trip updated");
    }
}