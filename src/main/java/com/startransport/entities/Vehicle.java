package com.startransport.entities;

public class Vehicle {
    private int seatCount;

    public Vehicle(int seatCount) {
        this.seatCount = seatCount;
    }

    public boolean hasAvailableSeats() {
        return seatCount > 0;
    }

    public void decrementSeatCount() {
        if(seatCount > 0) {
            seatCount--;
        }
    }

    public void incrementSeatCount() {
        seatCount++;
    }
}

