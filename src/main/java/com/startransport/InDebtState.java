package com.startransport;
import com.startransport.entities.Passenger;
import com.startransport.entities.Trip;

import java.util.ArrayList;

public class InDebtState implements AccountState {

    @Override
    public String getStatusMessage() {
        return "Account is in Debt";
    }

    @Override
    public double getTotalPayable(Passenger passenger) {
        double total = 0;
        Trip currentTrip = passenger.getCurrentTrip();
        if (currentTrip != null) {
            total += currentTrip.getCurrentFair();
        }
        ArrayList<Trip> pastTrips = passenger.getAllPastTrips();
        total += pastTrips.stream().filter(x -> !x.isPaid()).mapToDouble(Trip::getCurrentFair).sum();
        return total;

        //  currentTrip.getCurrentFair();

    }


}

