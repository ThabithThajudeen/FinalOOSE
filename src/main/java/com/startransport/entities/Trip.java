package com.startransport.entities;
import com.startransport.observers.Observer;
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
    private int initialStopCount;
    private int currentStopCount;
    private List<Observer<Trip>> observers = new ArrayList<>();
    private int startStopCount;
    private static final double PRICE_PER_STOP = 2.5;
    private boolean isCardSwiped;
    public Trip(String tripId, String passengerId, String vehicleId) {
        this.tripId = tripId;
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
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

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getInitialStopCount() {
        return initialStopCount;
    }

    public int getCurrentStopCount() {
        return currentStopCount;
    }

    public void setCurrentStopCount(int currentStopCount) {
        this.currentStopCount = currentStopCount;
    }

    public List<Observer<Trip>> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer<Trip>> observers) {
        this.observers = observers;
    }



    public boolean isCardSwiped() {
        return isCardSwiped;
    }


    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public int getStartStopCount() {
        return startStopCount;
    }

    public void setStartStopCount(int startStopCount) {
        this.startStopCount = startStopCount;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }
    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public boolean isOngoing() {
        return isOngoing;
    }

    public void setOngoing(boolean ongoing) {
        isOngoing = ongoing;
    }

    public boolean isPaid() {
        return isPaid;
    }



    public void setInitialStopCount(int initialStopCount) {
        this.initialStopCount = initialStopCount;
    }



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

    @Override
    public void update(Vehicle vehicle) {
        int vehicleStopCount= vehicle.getCurrentStopCount();
        this.currentStopCount = this.initialStopCount-vehicleStopCount;

    }
    public void setCardSwiped(boolean isCardSwiped) {
        this.isCardSwiped = isCardSwiped;
        this.notifyAllTripObservers();
    }


}