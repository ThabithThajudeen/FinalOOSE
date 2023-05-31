package com.startransport.entities;

import com.startransport.observers.Observer;
//import com.startransport.observers.TripObserver;
import com.startransport.states.InDebtState;
import com.startransport.states.NotOnboard;
import com.startransport.states.OnboardState;

import java.util.ArrayList;
import java.util.List;

public class Passenger implements Observer<Trip> {
    private String passengerID;
    private String passengerName;
    private Trip currentTrip;
   // private Bus currentBusStop;

//    private Train currentTrainCount;
    private List<Trip> pastTrips = new ArrayList<>();
    private OnboardState onboardState;



    public Passenger(String passengerID, String passengerName) {
        this.passengerID = passengerID;
        this.passengerName = passengerName;

    }

    public Passenger(String passengerID) {
        this.passengerID = passengerID;

    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public List<Trip> getPastTrips() {
        return pastTrips;
    }

    public void setPastTrips(List<Trip> pastTrips) {
        this.pastTrips = pastTrips;
    }



//    public Train getCurrentTrainCount() {
//        return currentTrainCount;
//    }

//    public void setCurrentTrainCount(Train currentTrainCount) {
//        this.currentTrainCount = currentTrainCount;
//    }



    //public Bus getCurrentBusStop() {
     //   return currentBusStop;
   // }

   // public void setCurrentBusStop(Bus currentBusStop) {
     //   this.currentBusStop = currentBusStop;
   // }

    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    public Trip getCurrentTrip() {
        return currentTrip;
    }

    public void setCurrentTrip(Trip currentTrip) {
        this.currentTrip = currentTrip;
    }

    public void addToPastTrips(Trip trip) {
        pastTrips.add(trip);
    }
    public OnboardState getOnboardState() {
        return onboardState;
    }

    public void setOnboardState(OnboardState onboardState) {
        this.onboardState = onboardState;
    }


    public List<Trip> getAllPastTrips() {
        List<Trip> x = new ArrayList<>();
        x.addAll(pastTrips);
        return x;
    }
//    public void goOnboard(Vehicle vehicle) {
//        if(vehicle.hasAvailableSeats()) {
//            this.onboardState = new Onboard();
//            vehicle.decrementSeatCount();
//        } else {
//            System.out.println("Sorry, no available seats!");
//        }
//    }
//
//    public void leaveBoard(Vehicle vehicle) {
//        this.onboardState = new NotOnboard();
//        vehicle.incrementSeatCount();
//    }
//
//    public String checkStatus() {
//        return this.onboardState.getStatusMessage();
//    }



//    @Override
//    public void update(Trip trip) {
//        // Check if the trip has ended
//        if (!trip.isOngoing()) {
//            // Add to past trips and remove from current trip
//            pastTrips.add(trip);
//            currentTrip = null;
//
//            // Check if the card was swiped during the trip
//            if (trip.isCardSwiped()) {
//
//            } else {
//
//                onboardState = new InDebtState();
//            }
//        }
//    }

    @Override
    public void update(Trip trip) {
        // Check if the trip has ended
        if (!trip.isOngoing()) {
            // Add to past trips and remove from current trip
            pastTrips.add(trip);
            currentTrip = null;
            onboardState = new NotOnboard(); // Change state to NotOnboard

            // Check if the card was swiped during the trip
            if (!trip.isCardSwiped()) {
                onboardState = new OnboardState() {
                    @Override
                    public String getStatusMessage() {
                        return "Passenger is onboard";
                    }
                };
            }
        } else {
            onboardState = new OnboardState() {
                @Override
                public String getStatusMessage() {
                    return "Passenger is notOnboard";
                }
            }; // Change state to Onboard if the trip is ongoing
        }
    }


}