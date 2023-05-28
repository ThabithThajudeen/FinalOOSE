package com.startransport;
public class NotBoardState implements CommuterState {

    @Override
    public void handleState(Commuter commuter) {
        System.out.println("Passenger is not onboard");
    }
}
