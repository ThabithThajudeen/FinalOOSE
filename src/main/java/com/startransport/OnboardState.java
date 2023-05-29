package com.startransport;

//import org.example.Commuter;

public abstract class OnboardState implements CommuterState {

    @Override
    public void handleState(Commuter commuter) {
        System.out.println("Passenger is OnBoard");
    }


    public abstract String getStatusMessage();
}

