package com.startransport.events;


public class CardSwiped extends Event{
  private String passengerId;
  public CardSwiped(String eventId) {
        super(eventId);
    }

    public String getPassengerId() {
        return passengerId;
    }


}
