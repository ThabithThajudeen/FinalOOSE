package com.startransport.entities;

import com.startransport.observers.TrainObserver;

import java.util.ArrayList;

public class Train {
    private String passengerID;

    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    public String getTrainID() {
        return trainID;
    }

    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }

    public ArrayList<TrainObserver> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<TrainObserver> observers) {
        this.observers = observers;
    }

    private String trainID;
    private ArrayList<TrainObserver> observers = new ArrayList<>();


    public Train(String passengerID, String trainID){
        this.passengerID = passengerID;
        this.trainID = trainID;

    }

    public void attachObserver(TrainObserver observer){
        this.observers.add(observer);
    }
    public void notifyAllTripObservers(){
        for (TrainObserver o: observers){
            o.updateCurrentTrainStand();

        }
    }

}