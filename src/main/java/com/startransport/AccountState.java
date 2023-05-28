package com.startransport;

import com.startransport.entities.Passenger;

public interface AccountState {
    String getStatusMessage();
    double getTotalPayable(Passenger passenger);
}
