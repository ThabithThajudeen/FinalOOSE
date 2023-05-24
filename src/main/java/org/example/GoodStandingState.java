package org.example;

public class GoodStandingState implements AccountState {

    @Override
    public String getStatusMessage() {
        return "Account is in good Standing";
    }
}

