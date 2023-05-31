package com.startransport.events;


public class CardSwiped extends Event{
    private String passengerId;
    private String vehicleType;
    private String vehicleId;

    public CardSwiped(String eventId) {
        super(eventId);
    }

//    public CardSwiped(String eventId, boolean isCardSwiped) {
  //      super(eventId);
   // }

    public void cardSwipedEvent(String passengerId) {
      //  super();
        this.passengerId = passengerId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }
}
