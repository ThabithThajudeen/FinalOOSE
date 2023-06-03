package com.startransport.entities;
import com.startransport.observers.Observer;
import com.startransport.states.AccountState;
import com.startransport.states.GoodStandingState;
import com.startransport.states.InDebtState;
import com.startransport.states.NotOnboard;
import com.startransport.states.Onboard;
import com.startransport.states.OnboardState;



import java.util.ArrayList;
import java.util.List;

public class Passenger implements Observer<Trip> {
    private String passengerID;
    private String passengerName;
    private Trip currentTrip;
    private AccountState accountState;
    private OnboardState onboardState;
    private List<Trip> pastTrips = new ArrayList<>();

    public Passenger(String passengerID, String passengerName) {
        this.passengerID = passengerID;
        this.passengerName = passengerName;
        this.accountState = new GoodStandingState();

    }
    public AccountState getAccountState() {
        return accountState;
    }

    public void setAccountState(AccountState accountState) {
        this.accountState = accountState;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPassengerID() {
        return passengerID;
    }

    public Trip getCurrentTrip() {
        return currentTrip;
    }

    public void setCurrentTrip(Trip currentTrip) {
        this.currentTrip = currentTrip;
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

    @Override
    public void update(Trip trip) {
        // Check if the trip has ended
        if (!trip.isOngoing()) {
            // Add to past trips and remove from current trip
            pastTrips.add(trip);
            currentTrip = null;
            onboardState = new NotOnboard(); // Change state to NotOnboard

            // Check if the trip was paid for
            if (!trip.isPaid()) {
                accountState= new InDebtState(); // Set debtState to InDebtState if the trip was not paid
            } else {
                accountState = new GoodStandingState(); // Set debtState to GoodStandingState if the trip was paid
            }
        } else {
            onboardState = new Onboard(); // Change state to Onboard if the trip is ongoing
        }
    }
    public double getTotalPayable(){
        return this.accountState.getTotalPayable(this);
    }

}