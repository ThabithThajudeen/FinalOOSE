//package com.startransport.entities;
//
//import com.startransport.observers.TripObserver;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//public class Trip {
//    private String passengerId;
//    private String tripId;
//    private LocalDateTime timeStart;
//    private LocalDateTime timeEnd;
//    private String vehicleId;
//    private boolean isOngoing;
  //  private boolean isPaid;
//    private Train currentTrainStop;
//    private Bus currentBusCount;
//    private ArrayList<TripObserver> observers = new ArrayList<>();
//// initial bus passed count=5; -> bus stop he gets in
//    // attach the trip -> event
//    //
//
//
//    public Trip(String tripId,String passengerId, String vehicleId) {
//
//        this.tripId = tripId;
//        this.passengerId = passengerId;
//        this.vehicleId = vehicleId;
//
//    }
//
//    public boolean isPaid() {
//        return isPaid;
//    }
//
//    public void setPaid(boolean paid) {
//        isPaid = paid;
//    }
//
//
//
//    public boolean isOngoing() {
//        return isOngoing;
//    }
//
//    public void setOngoing(boolean ongoing) {
//        isOngoing = ongoing;
//    }
//
//
//
//    public double getCurrentFair() {
//        return currentFair;
//   }
//
//    public Bus getCurrentBusCount() {
//        return currentBusCount;
//    }
//
//    public void setCurrentBusCount(Bus currentBusCount) {
//        this.currentBusCount = currentBusCount;
//    }
//    public Train getCurrentTrainStop() {
//        return currentTrainStop;
//    }
//
//    public void setCurrentTrainStop(Train currentTrainStop) {
//        this.currentTrainStop = currentTrainStop;
//    }
//    public void setCurrentFair(double currentFair) {
//        this.currentFair = currentFair;
//  }
//

//
//    public String getPassengerId() {
//        return passengerId;
//    }
//
//    public void setPassengerId(String passengerId) {
//        this.passengerId = passengerId;
//    }
//
//    public String getTripId() {
//        return tripId;
//    }
//
//    public void setTripId(String tripId) {
//        this.tripId = tripId;
//    }
//
//    public LocalDateTime getTimeStart() {
//        return timeStart;
//    }
//
//    public void setTimeStart(LocalDateTime timeStart) {
//        this.timeStart = timeStart;
//    }
//
//    public LocalDateTime getTimeEnd() {
//        return timeEnd;
//    }
//
//    public void setTimeEnd(LocalDateTime timeEnd) {
//        this.timeEnd = timeEnd;
//        this.notifyAllTripObservers();
//    }
//
//    public String getVehicleId() {
//        return vehicleId;
//    }
//
//    public void setVehicleId(String vehicleId) {
//        this.vehicleId = vehicleId;
//    }
//
//    public void attachObserver(TripObserver observer){
//        this.observers.add(observer);
//    }
//    public void notifyAllTripObservers(){
//        for (TripObserver o: observers){
//            o.updateCurrentTrip();
//
//        }
//    }
//
//
//
//
//
//
//    // Constructor
//
//}

package com.startransport.entities;

import com.startransport.observers.Observer;
import com.startransport.observers.TripObserver;
//import com.startransport.observers.VehicleObserver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Trip implements Observer<Vehicle> {

    private String passengerId;
    private String tripId;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private String vehicleId;
    private boolean isOngoing;
    private boolean isPaid;
//    private Train currentTrainStop;
//    private Bus currentBusStop;  // renamed to match type
    private int initialStopCount;
    private int currentStopCount;
    private List<Observer<Trip>> observers = new ArrayList<>();
    private int startStopCount;

//    private Bus currentBusCount;
    private static final double PRICE_PER_STOP = 2.5;
    private boolean isCardSwiped;

    public boolean isCardSwiped() {
        return isCardSwiped;
    }


    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

  //  public Bus getCurrentBusCount() {
   //     return currentBusCount;
   // }

//    public void setCurrentBusCount(Bus currentBusCount) {
//        this.currentBusCount = currentBusCount;
//    }
    public int getStartStopCount() {
        return startStopCount;
    }

    public void setStartStopCount(int startStopCount) {
        this.startStopCount = startStopCount;
    }
//    public String getPassengerId() {
//        return passengerId;
//    }

//   // public void setPassengerId(String passengerId) {
//        this.passengerId = passengerId;
//    }

    public String getTripId() {
        return tripId;
    }

//    public void setTripId(String tripId) {
//        this.tripId = tripId;
//    }

//    public LocalDateTime getTimeStart() {
//        return timeStart;
//    }

//    public void setTimeStart(LocalDateTime timeStart) {
//        this.timeStart = timeStart;
//    }

//    public LocalDateTime getTimeEnd() {
//        return timeEnd;
//    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

//    public String getVehicleId() {
//        return vehicleId;
//    }

//    public void setVehicleId(String vehicleId) {
//        this.vehicleId = vehicleId;
//    }

    public boolean isOngoing() {
        return isOngoing;
    }

    public void setOngoing(boolean ongoing) {
        isOngoing = ongoing;
    }

    public boolean isPaid() {
        return isPaid;
    }

//    public void setPaid(boolean paid) {
//        isPaid = paid;
//    }

//    public Train getCurrentTrainStop() {
//        return currentTrainStop;
//    }

//    public void setCurrentTrainStop(Train currentTrainStop) {
//        this.currentTrainStop = currentTrainStop;
//    }

//    public Bus getCurrentBusStop() {
//        return currentBusStop;
//    }
//
//    public void setCurrentBusStop(Bus currentBusStop) {
//        this.currentBusStop = currentBusStop;
//    }
//
//    public int getInitialStopCount() {
//        return initialStopCount;
//    }

//    public int getCurrentStopCount() {
//        return currentStopCount;
//    }

//    public List<TripObserver> getObservers() {
//        return observers;
//    }
//
//    public void setObservers(List<TripObserver> observers) {
//        this.observers = observers;
//    }


    public Trip(String tripId, String passengerId, String vehicleId) {
        this.tripId = tripId;
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
    }

    public void setInitialStopCount(int initialStopCount) {
        this.initialStopCount = initialStopCount;
    }

//    public void setCurrentStopCount(int currentStopCount) {
//        this.currentStopCount = currentStopCount;
//    }

    public int getStopsPassed() {
        return currentStopCount - initialStopCount;
    }
    public void attachObserver(Observer<Trip> observer){
        this.observers.add(observer);
    }

    public void notifyAllTripObservers(){
        for (Observer<Trip> o: observers){
            o.update(this);
        }
    }
    public double calculateFare() {
        int stopsPassed = getStopsPassed();
        return stopsPassed * PRICE_PER_STOP;
    }
    public double getCurrentFair() {
        return calculateFare();
    }

//    @Override
//    public void updateCurrentTrip() {
//        if (this.isOngoing()) {
//            if (this.getCurrentBusStop() != null) {
//                this.setCurrentStopCount(this.getCurrentBusStop().getStopsPassed());
//            } else if (this.getCurrentTrainStop() != null) {
//                this.setCurrentStopCount(this.getCurrentTrainStop().getStopsPassed());
//            }
//        }
//    }


//    @Override
//    public void updateCurrentVehicle(Vehicle vehicle) {
//        int vehicleStopCount= vehicle.getCurrentStopCount();
//        this.currentStopCount = this.initialStopCount-vehicleStopCount;
//
//    }

    @Override
    public void update(Vehicle vehicle) {
        int vehicleStopCount= vehicle.getCurrentStopCount();
        this.currentStopCount = this.initialStopCount-vehicleStopCount;

    }
    public void setCardSwiped(boolean isCardSwiped) {
        this.isCardSwiped = isCardSwiped;
        this.notifyAllTripObservers();
    }
//    public void endTrip() {
//        // Set the end time of the trip to now
//        this.timeEnd = LocalDateTime.now();
//
//        // Set the ongoing status to false
//        this.isOngoing = false;
//    }

}