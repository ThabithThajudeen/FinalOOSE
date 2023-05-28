package com.startransport;

public class Commuter {
    private String id;
    private CommuterState commuterState;

    public Commuter(String id) {
        this.id = id;
        this.commuterState = new NotBoardState(); // Default state is NotOnBoard
    }

    public void setCommuterState(CommuterState state) {
        this.commuterState = state;
        this.commuterState.handleState(this);
    }

    public String getOnboardStatus() {
        if (this.commuterState instanceof OnboardState) {
            return "OnBoard";
        } else if (this.commuterState instanceof NotBoardState) {
            return "Not OnBoard";
        } else {
            return "Unknown status";
        }
    }
}