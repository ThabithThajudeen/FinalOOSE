package com.startransport.entities;
import com.startransport.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Passenger {
    private AccountState accountState;
    private LocalDateTime paymentRequiredTime;
    private LocalDateTime paymentMadeTime;
    //private OnboardState onboardState;
    private String vehicleId;
    private ArrayList<Trip> pastTrips = new ArrayList<>();
    private Trip currentTrip;

    public Passenger(AccountState accountState, LocalDateTime paymentRequiredTime, LocalDateTime paymentMadeTime, OnboardState onboardState, String vehicleId) {
        this.accountState = accountState;
        this.paymentRequiredTime = paymentRequiredTime;
        this.paymentMadeTime = paymentMadeTime;
        this.vehicleId = vehicleId;
        updateAccountState();
    }


    public Passenger() {
     //   onboardState = new NotOnboard();
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
    public void enterVehicle(String vehicleId) {
        this.vehicleId = vehicleId;
      //  onboardState = new Onboard();
    }

    public void leaveVehicle() {
        this.vehicleId = null;
        //onboardState = new NotOnboard();
    }

//    public String getOnboardStatus() {
//        return onboardState.getStatusMessage();
//    }

    public String getCurrentVehicle() {
        return vehicleId;
    }
    private void updateAccountState() {
        if (paymentMadeTime == null) {
            accountState = new CancelledState();
        } else if (paymentMadeTime.isBefore(paymentRequiredTime)) {
            accountState = new InDebtState();
        } else {
            accountState = (AccountState) new GoodStandingState();
        }
    }
    public String getAccountStatus() {
        return accountState.getStatusMessage();
    }

}