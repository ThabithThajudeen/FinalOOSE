package com.startransport.events;

public class AccountActivated extends Event{



    private String passengerID;

    public AccountActivated(String passengerID) {
        super( passengerID);


    }

    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }


}
