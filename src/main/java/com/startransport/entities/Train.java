//package com.startransport.entities;
//
//import com.startransport.observers.VehicleObserver;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Train {
//    private String passengerID;
//    private String trainID;
//    private List<VehicleObserver> observers = new ArrayList<>();
//    private int initialStopCount;
//    private int currentStopCount;
//    private int tripPassedCount;
//
//    public int getTripPassedCount() {
//        return tripPassedCount;
//    }
//
//    public void incrementStopCount() {
//        this.currentStopCount++;
//    }
//
//    public void setTripPassedCount(int tripPassedCount) {
//        this.tripPassedCount = tripPassedCount;
//    }
//
//    public int getInitialStopCount() {
//        return initialStopCount;
//    }
//
//    public void setInitialStopCount(int initialStopCount) {
//        this.initialStopCount = initialStopCount;
//    }
//
//    public int getCurrentStopCount() {
//        return currentStopCount;
//    }
//
//    public void setCurrentStopCount(int currentStopCount) {
//        this.currentStopCount = currentStopCount;
//    }
//
//
//
//    public Train(String passengerID) {
//        this.passengerID = passengerID;
//        this.trainID = trainID;
//
//    }
//
//    public String getPassengerID() {
//        return passengerID;
//    }
//
//    public void setPassengerID(String passengerID) {
//        this.passengerID = passengerID;
//    }
//
//    public String getTrainID() {
//        return trainID;
//    }
//
//    public void setTrainID(String trainID) {
//        this.trainID = trainID;
//    }
//
//    public List<VehicleObserver> getObservers() {
//        return observers;
//    }
//
//    public void setObservers(List<VehicleObserver> observers) {
//        this.observers = observers;
//    }
//
//    public void attachObserver(VehicleObserver observer) {
//        this.observers.add(observer);
//    }
//
////    public void notifyAllTripObservers() {
////        for (VehicleObserver o : observers) {
////            o.updateCurrentstand();
////
////        }
////    }
//    public int getStopsPassed() {
//        return currentStopCount - initialStopCount;
//    }
//
//}