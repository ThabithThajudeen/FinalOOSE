package com.startransport.states;
import com.startransport.entities.Passenger;
import com.startransport.entities.Trip;


//import java.util.ArrayList;
import java.util.List;

public class InDebtState implements AccountState {

    @Override
    public String getStatusMessage() {
        return "Debt";
    }

    @Override
    public double getTotalPayable(Passenger passenger) {
        double total = 0;
        Trip currentTrip = passenger.getCurrentTrip();
        if (currentTrip != null) {
            total += currentTrip.getCurrentFair();
        }
        List<Trip> pastTrips = passenger.getAllPastTrips();
        total += pastTrips.stream().filter(x -> !x.isPaid()).mapToDouble(Trip::getCurrentFair).sum();
        return total;

        //  currentTrip.getCurrentFair();

    }


}

