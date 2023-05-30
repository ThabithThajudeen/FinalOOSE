package com.startransport.states;

import com.startransport.entities.Passenger;

public interface AccountState {
    String getStatusMessage();
    double getTotalPayable(Passenger passenger);
}
