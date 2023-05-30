package com.startransport.states;

import com.startransport.entities.Vehicle;

public class Onboard extends OnboardState {
    private OnboardState onboardState;
    public String getStatusMessage() {
        return "Onboard";
    }
    public OnboardState getOnboardState() {
        return onboardState;
    }
    public String checkStatus() {
        return this.onboardState.getStatusMessage();
    }

    public void setOnboardState(OnboardState onboardState) {
        this.onboardState = onboardState;
    }
    public void goOnboard(Vehicle vehicle) {
        if(vehicle.hasAvailableSeats()) {
            this.onboardState = new Onboard();
            vehicle.decrementSeatCount();
        } else {
            System.out.println("Sorry, no available seats!");
        }
    }

}
