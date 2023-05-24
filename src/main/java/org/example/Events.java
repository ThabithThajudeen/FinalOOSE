package org.example;

import java.time.LocalDateTime;

public class Events {
    private String eventId;
    private String eventType;
    private String entityId;
    private LocalDateTime eventTime;
    private LocalDateTime paymentRequiredTime;
    private LocalDateTime paymentMadeTime;

    public LocalDateTime getScheduledArrivalTime() {
        return ScheduledArrivalTime;
    }

    public void setScheduledArrivalTime(LocalDateTime scheduledArrivalTime) {
        ScheduledArrivalTime = scheduledArrivalTime;
    }

    private LocalDateTime ScheduledArrivalTime;

    // Constructor
    public Events(String eventId, String eventType, String entityId, LocalDateTime eventTime,
                 LocalDateTime paymentRequiredTime, LocalDateTime paymentMadeTime,LocalDateTime ScheduledArrivalTime ) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.entityId = entityId;
        this.eventTime = eventTime;
        this.paymentRequiredTime = paymentRequiredTime;
        this.paymentMadeTime = paymentMadeTime;
        this.ScheduledArrivalTime = ScheduledArrivalTime;
    }

    // Getters and setters
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public LocalDateTime getPaymentRequiredTime() {
        return paymentRequiredTime;
    }

    public void setPaymentRequiredTime(LocalDateTime paymentRequiredTime) {
        this.paymentRequiredTime = paymentRequiredTime;
    }

    public LocalDateTime getPaymentMadeTime() {
        return paymentMadeTime;
    }

    public void setPaymentMadeTime(LocalDateTime paymentMadeTime) {
        this.paymentMadeTime = paymentMadeTime;
    }
}
