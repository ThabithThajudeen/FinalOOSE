package com.startransport.factories;

import com.google.gson.JsonObject;
import com.startransport.entities.Trip;
import com.startransport.entities.Passenger;
import com.startransport.errors.UnknownEventException;
import com.startransport.events.*;

import java.util.Map;

public class EventFactory {
    public Event getEvent(JsonObject data) throws UnknownEventException {
        String eventId = data.get("EventID").getAsString();
        String eventType = data.get("EventType").getAsString();
        Event event;
        if (eventType.equals("TRIP_STARTED")) {
            String passengerID = data.get("PassengerID").getAsString();
            String vehicleType = data.get("VehicleType").getAsString();
            String vehicleID = data.get("VehicleID").getAsString();
            VehicleType vehicleTypeEnum;
            if (vehicleType.equals("BUS")) {
                vehicleTypeEnum = VehicleType.BUS;
            } else {
                vehicleTypeEnum = VehicleType.TRAINS;
            }
            event = new TripStartedEvent(eventId, passengerID, vehicleTypeEnum, vehicleID);


        } else if (eventType.equals("TRIP_STOPPED")) {
            String passengerID = data.get("PassengerID").getAsString();
            event = new TripStoppedEvent(eventId, passengerID);

        } else if (eventType.equals("VEHICLE_PASSED_STOP")) {
            String vehicleType = data.get("VehicleType").getAsString();
            String vehicleID = data.get("VehicleID").getAsString();
            VehicleType vehicleTypeEnum;
            if (vehicleType.equals("Bus")) {
                vehicleTypeEnum = VehicleType.BUS;
            } else {
                vehicleTypeEnum = VehicleType.TRAINS;
            }
            event = new VehiclePassedStop(eventId,eventType,vehicleType,vehicleID);

            throw new UnknownEventException();
           // return event;
        }

        return event;
    }

}
