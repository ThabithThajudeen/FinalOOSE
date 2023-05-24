package org.example;

public class Bus {
    public enum Status {
        ON_TIME, DELAYED, CANCELLED
    }

    private int busID;
    private Status status;
    private double latitude;
    private double longitude;

    // Constructor
    public Bus(int busID, Status status, double latitude, double longitude) {
        this.busID = busID;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters
    public int getBusID() {
        return this.busID;
    }

    public Status getStatus() {
        return this.status;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    // Setters
    public void setBusID(int busID) {
        this.busID = busID;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

