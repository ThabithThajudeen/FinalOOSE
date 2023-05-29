package com.startransport;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.startransport.entities.Bus;
import com.startransport.entities.Passenger;
import com.startransport.entities.Train;
import com.startransport.entities.Trip;
import com.startransport.errors.UnknownEventException;
import com.startransport.events.*;
//import com.startransport.events.BusPassedBusStop;
import com.startransport.factories.EventFactory;
import com.startransport.observers.BusObserver;
import com.startransport.observers.TrainObserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String TRIP_ID_PREFIX = "T-";
    private static final String TRAIN_ID_PREFIX = "TRAIN-";
    private static final String BUS_ID_PREFIX = "BUS-";
    private static final String PASSENGER_ID_PREFIX = "PASSENGER-";
    private static final String EVENT_ID_PREFIX = "EVENT-";
    private static int tripIdCount = 1;
    private static int eventIdCount = 1;
    private static int busStopPassed = 1;
    private static int trainStopPassed = 1;

    private static int busCount = 1;
    private static int trainCount = 1;

    static private EventFactory eventFactory = new EventFactory();
    private static Map<String, Passenger> passengers = new HashMap<>();
    private static Map<String, Bus> buses = new HashMap<>();
    private static Map<String, Train> trains = new HashMap<>();
    private static Map<String, Trip> trips = new HashMap<>();
    private static Map<String, Event> events = new HashMap<>();

    public static void main(String[] args) throws UnknownEventException {
        System.out.println("hii");

        try {
            InputStream inputStream = Main.class.getResourceAsStream("/Passengers.csv");
            Scanner scanner = new Scanner(new InputStreamReader(inputStream));

            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");

                // Assuming the first field is passengerID and the second field is name
                String passengerID = data[0];
                String passengerName = data[1];

                // Create a new passenger with the read ID and name
                Passenger passenger1 = new Passenger(passengerID, passengerName);

                // Store the new passenger in the map using passengerID as the key
                passengers.put(passengerID, passenger1);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        try {
            InputStream inputStream = Main.class.getResourceAsStream("/buses.csv");
            Scanner scanner = new Scanner(new InputStreamReader(inputStream));

            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");

                // Assuming the first field is busID and the second field is passengerID
                String busID = data[0];
                String passengerID = data[1];

                // Create a new bus with the read bus ID
                Bus bus = new Bus(busID);

                // If the bus already exists in the map, get it
                if (buses.containsKey(busID)) {
                    bus = buses.get(busID);
                }
                // Add the passenger to the bus
                if (passengers.containsKey(passengerID)) {
                    Passenger passenger = passengers.get(passengerID);

                }
                // Store the new bus in the map using busID as the key
                buses.put(busID, bus);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        try {
            InputStream inputStream = Main.class.getResourceAsStream("/trains.csv");
            Scanner scanner = new Scanner(new InputStreamReader(inputStream));

            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");

                // Assuming the first field is trainID, the second field is passengerID, and the third field is status
                String trainID = data[0];
                String passengerID = data[1];
                String status = data[2];

                // Create a new train with the read train ID and status
                Train train = new Train(trainID, status);

                // If the train already exists in the map, get it
                if (trains.containsKey(trainID)) {
                    train = trains.get(trainID);
                }

                // Add the passenger to the train
//                if (passengers.containsKey(passengerID)) {
//                    Passenger passenger = passengers.get(passengerID);
//                    train.addPassenger(passenger);
//                }

                // Store the new train in the map using trainID as the key
                trains.put(trainID, train);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }


        try {
            InputStream inputStream = Main.class.getResourceAsStream("/events.json");
            JsonParser jsonParser = new JsonParser();
            JsonElement e = jsonParser.parse(new InputStreamReader(inputStream));
            JsonArray array = e.getAsJsonArray();
            System.out.println("String" + array.size());
            for (int i = 0; i < array.size(); i++) {


                Event event = eventFactory.getEvent(array.get(i).getAsJsonObject());
                if (event instanceof TripStartedEvent) {
                    TripStartedEvent e1 = (TripStartedEvent) event;
                    Passenger passenger = passengers.get(e1.getPassengerId());
                    Trip trip = new Trip(TRIP_ID_PREFIX + (tripIdCount++), e1.getPassengerId(), "v1");
                    passenger.setCurrentTrip(trip);
                    trips.put(trip.getTripId(), trip);
                    trip.attachObserver(passenger);

                } else if (event instanceof TripStoppedEvent) {
                    TripStoppedEvent e1 = (TripStoppedEvent) event;
                    Passenger passenger = passengers.get(e1.getPassengerId());
                    Trip trip = passenger.getCurrentTrip();
                    trip.setTimeEnd(LocalDateTime.now());
                    trip.setOngoing(false);
                }
                if (event instanceof VehiclePassedStop) {
                    {
                        VehiclePassedStop v1 = (VehiclePassedStop) event;
                        Passenger passenger = passengers.get(v1.getPassengerID());
                        if (v1.getVehicleType().equals("bus")) {
                            Bus bus = buses.get(BUS_ID_PREFIX + (busStopPassed++) + v1.getPassengerID());
                            if (bus != null) {
                                passenger.setCurrentBusStop(bus);
                                bus.attachObserver((BusObserver) passenger);
                            } else {
                                System.out.println("Bus not found");
                            }
                        } else if (v1.getVehicleType().equals("train")) {
                            Train train = trains.get(TRAIN_ID_PREFIX + (trainStopPassed++) + v1.getPassengerID());
                            if (train != null) {
                                passenger.setCurrentTrainStop(train);
                                train.attachObserver((TrainObserver) passenger);
                            } else {
                                System.out.println("Train not found");
                            }
                        }
                        if (event instanceof VehicleCount) {
                            VehicleCount vc1 = (VehicleCount) event;
                            Passenger passenger1 = passengers.get(v1.getPassengerID());
                            if (v1.getVehicleType().equals("bus")) {
                                Bus bus = buses.get(BUS_ID_PREFIX + (busCount++) + vc1.getPassengerId());
                                if (bus != null) {
                                    passenger.setCurrentBusCount(bus);
                                    bus.attachObserver((BusObserver) passenger1);
                                } else ((vc1.getVehicleType().equals("train")) {
                                    Train train = trains.get(TRAIN_ID_PREFIX + (trainCount++) + vc1.getPassengerId());
                                    if (train != null) {
                                        passenger1.setCurrentTrainCount(train);
                                        train.attachObserver((TrainObserver) passenger1);
                                    } else {
                                        System.out.println("Train not found");
                                    }
                                }
                            }

                        }
                    }


                }

            }