package com.startransport.states;
import com.startransport.entities.Passenger;
import com.startransport.entities.Trip;


//import java.util.ArrayList;
import java.util.List;

public class CancelledState implements AccountState {
    public final static double ACTIVATION_PENALTY = 100;

    @Override
    public String getStatusMessage() {
        return "cancelled";
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
        total+= ACTIVATION_PENALTY; // -> penalty is added in case to recover from cancelled to active state.
        return total;

        //  currentTrip.getCurrentFair();

    }

}
