package org.example;

public class InDebtState implements AccountState{

    @Override
    public String getStatusMessage() {
        return "Account is in Debt";
    }
}
