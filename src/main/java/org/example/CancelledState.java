package org.example;

public class CancelledState implements AccountState{

    @Override
    public String getStatusMessage() {
        return "Account is Cancelled";
    }
}
