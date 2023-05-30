package com.startransport.states;

import com.startransport.entities.Passenger;
import com.startransport.entities.Trip;


public class GoodStandingState implements AccountState {

    @Override
    public String getStatusMessage() {
        return "Account is in good Standing";
    }

    @Override
    public double getTotalPayable(Passenger passenger) {
        if(passenger.getCurrentTrip()==null){
            return 0;
        }else {
            Trip currentTrip = passenger.getCurrentTrip();
            return currentTrip.getCurrentFair();

        }
    }

}

