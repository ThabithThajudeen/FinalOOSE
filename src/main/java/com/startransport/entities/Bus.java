//package com.startransport.entities;
//
//import com.startransport.observers.VehicleObserver;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Bus{
//    private String passengerID;
//   // private String busID;
//    private List<VehicleObserver> observers = new ArrayList<>();
//    private int initialStopCount;
//    private int currentStopCount;
//    private int tripPassedCount;
//    public int getTripPassedCount() {
//        return tripPassedCount;
//    }
//
//    public void setTripPassedCount(int tripPassedCount) {
//        this.tripPassedCount = tripPassedCount;
//    }
//    public void incrementStopCount() {
//        this.currentStopCount++;
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
////    public Bus(String vehicleID,int seatCount,int totalSeatCount) {
////        super(vehicleID,seatCount,totalSeatCount);
////
////    }
//
//
////    public Bus(String vehicleID) {
////        super();
////        this.busID = vehicleID;
////    }
//
//    public String getPassengerID() {
//        return passengerID;
//    }
//
//    public void setPassengerID(String passengerID) {
//        this.passengerID = passengerID;
//    }
//
////    public String getBusID() {
////        return busID;
////    }
//
////    public void setBusID(String busID) {
////        this.busID = busID;
////    }
//
//
//    public int getStopsPassed() {
//        return currentStopCount - initialStopCount;
//    }
//
//}