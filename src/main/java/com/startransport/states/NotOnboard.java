package com.startransport.states;

import com.startransport.entities.Vehicle;

public class NotOnboard extends OnboardState {
    private OnboardState onboardState;

    @Override

    public String getStatusMessage() {
        return "Not Onboard";
    }
//    public void leaveBoard(Vehicle vehicle) {
//        this.onboardState = new NotOnboard();
//        vehicle.incrementSeatCount();
//    }
//    public String checkStatus() {
//        return this.onboardState.getStatusMessage();
//    }
}
