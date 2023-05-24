package org.example;

public class Trains {
    public enum Status {
        ON_TIME, DELAYED, CANCELLED
    }

    private int trainID;
    private Status status;
    private double latitude;
    private double longitude;

    // Constructor
    public Trains(int trainID, Status status, double latitude, double longitude) {
        this.trainID = trainID;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters
    public int getTrainID() {
        return this.trainID;
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
    public void setTrainID(int trainID) {
        this.trainID = trainID;
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