package org.example;

public class Passengers {
    public enum AccountStatus {
        ACTIVE, SUSPENDED
    }

    //private String passengerName;
    private int passengerID;
    private String passengerName;
    private AccountStatus accountStatus;

    // Constructor
    public Passengers(int passengerID,String passengerName, AccountStatus accountStatus) {
        this.passengerName = passengerName;
        this.passengerID = passengerID;
        this.accountStatus = accountStatus;
    }

    // Getters
    public String getPassengerName() {
        return this.passengerName;
    }

    public int getPassengerID() {
        return this.passengerID;
    }

    public AccountStatus getAccountStatus() {
        return this.accountStatus;
    }

    // Setters
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
