package com.startransport.events;

import java.util.ArrayList;
import java.util.List;

public class EventSubject {
    private List<EventObserver> observers = new ArrayList<>();

    public void addObserver(EventObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(EventObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers(Event event) {
        for (EventObserver observer : this.observers) {
            observer.update(event);
        }
    }
}
