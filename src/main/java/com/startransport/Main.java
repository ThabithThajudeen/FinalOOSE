package com.startransport;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.*;
import com.startransport.entities.*;
import com.startransport.errors.JsonProcessingException;
import com.startransport.errors.UnknownEventException;
import com.startransport.errors.UnknownEventProcessingException;
import com.startransport.events.*;
//import com.startransport.events.BusPassedBusStop;
import com.startransport.factories.EventFactory;
import com.startransport.observers.VehicleObserver;

import java.io.*;
import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String TRIP_ID_PREFIX = "T-";
   // private static final String TRAIN_ID_PREFIX = "TRAIN-";
    //private static final String BUS_ID_PREFIX = "BUS-";
    //  private static final String PASSENGER_ID_PREFIX = "PASSENGER-";
    // private static final String EVENT_ID_PREFIX = "EVENT-";
    private static int tripIdCount = 1;
    //private static int eventIdCount = 1;
   // private static int busStopPassed = 1;
   // private static int trainStopPassed = 1;

    private static int busCount = 1;
    private static int trainCount = 1;

    static private EventFactory eventFactory = new EventFactory();
    private static Map<String, Passenger> passengers = new HashMap<>();
    private static Map<String, Bus> buses = new HashMap<>();
    private static Map<String, Train> trains = new HashMap<>();
    private static Map<String, Vehicle> vehicles = new HashMap<>();

    private static Map<String, Trip> trips = new HashMap<>();
    //private static Map<String, Event> events = new HashMap<>();

    public static void main(String[] args) throws UnknownEventException {
        System.out.println("hii");
        LOGGER.log(Level.INFO, "Application started");
        try (FileReader reader = new FileReader("src/main/resources/Passengers.csv");
             Scanner scanner = new Scanner(reader)) {

            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");

                // Assuming the first field is passengerID and the second field is name
                String passengerID = data[0];
                String passengerName = data[1];

                // Create a new passenger with the read ID and name
                Passenger passenger1 = new Passenger(passengerID, passengerName);

                // Store the new passenger in the map using passengerID as the key
                passengers.put(passengerID, passenger1);
                LOGGER.log(Level.INFO, "Passengers loaded successfully");
                // scanner.close();

            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while loading passengers", e);
            throw new UnknownEventException();
        }
        LOGGER.log(Level.INFO, "Application ended");
        System.out.println("hii2");
        try (FileReader reader = new FileReader("src/main/resources/Bus.csv");
             Scanner scanner = new Scanner(reader)) {

            LOGGER.log(Level.INFO, "Bus reading has started");
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
  //                  Passenger passenger = passengers.get(passengerID);
                }

                // Store the new bus in the map using busID as the key
                buses.put(busID, bus);
                // scanner.close();
            }
            LOGGER.log(Level.INFO, "Buses loaded successfully");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while loading buses", e);
            throw new UnknownEventException();
        }

        LOGGER.log(Level.INFO, "bus reading and loaded ended");
        System.out.println("Hii04");

        try (FileReader reader = new FileReader("src/main/resources/trains.csv");
             Scanner scanner = new Scanner(reader)) {
            LOGGER.log(Level.INFO, "Starting to load trains from trains.csv");
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");

                // Assuming the first field is trainID, the second field is passengerID, and the third field is status
                String trainID = data[0];
      //          String passengerID = data[1];
//                String status = data[2];

                // Create a new train with the read train ID and status
                Train train = new Train(trainID);

                // If the train already exists in the map, get it
                if (trains.containsKey(trainID)) {
                    train = trains.get(trainID);
                }

                // Store the new train in the map using trainID as the key
                trains.put(trainID, train);
            }
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.log(Level.INFO, "Successfully loaded trains from trains.csv");
            }
        } catch (FileNotFoundException e) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, "File not found: " + e.getMessage(), e);
            }
        } catch (IOException e) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, "Error closing resources: " + e.getMessage(), e);
            }
        }

        try (InputStream inputStream = Main.class.getResourceAsStream("/events.json")) {
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
                    trip.setStartStopCount(e1.getStartStopCount());
                    passenger.setCurrentTrip(trip);
                    trips.put(trip.getTripId(), trip);
                    trip.attachObserver(passenger);
                    Vehicle v=vehicles.get(e1.getVehicleID());
                    if (v != null){
                        v.attachObserver(trip);
                        trip.setInitialStopCount(v.getCurrentStopCount());
                    }
//                   // if (e1.getVehicleType().equals("BUS")) {
//                        Bus bus = buses.get(e1.getVehicleID());
//
//                        if (bus != null) {
//                            bus.attachObserver(trip);
//                            trip.setInitialStopCount(bus.getCurrentStopCount());
//                        }
//                    } else if (e1.getVehicleType().equals("train")) {
//                        Train train = trains.get(e1.getVehicleID());
//                        if (train != null) {
//                            train.attachObserver((VehicleObserver) trip);
//                        }
//                    }
                    //vehicle.attachObserver(trip)
                } else if (event instanceof TripStoppedEvent) {
                    TripStoppedEvent e1 = (TripStoppedEvent) event;
                    Passenger passenger = passengers.get(e1.getPassengerId());
                    Trip trip = passenger.getCurrentTrip();
                    trip.setTimeEnd(LocalDateTime.now());
                    trip.setOngoing(false);
                }
                if (event instanceof VehiclePassedStop) {
                    VehiclePassedStop v1 = (VehiclePassedStop) event;
                    //  Passenger passenger = passengers.get(v1.getPassengerID());
                    Trip trip = trips.get(v1.getPassengerID());
                    Vehicle v=vehicles.get(v1.getVehicleID());
                    if (v != null){
                        //v.attachObserver(trip);
                        v.incrementStopCount();
                        //trip.setInitialStopCount(v.getCurrentStopCount());
                    }

//                    if (v1.getVehicleType().equals("BUS")) { // if instance is bus
//                        Bus bus = buses.get(v1.getVehicleID()); // get the bus from the bus hashmap
//                        if (bus != null) { // if bus is not null
//                            bus.incrementStopCount(); // Increment bus stop count
//                            trip.setCurrentBusCount(bus);
//                            // bus.attachObserver((BusObserver) trip);
//                        }
//                        if (LOGGER.isLoggable(Level.INFO)) {
//                            LOGGER.log(Level.INFO, "Bus found and Passenger updated");
//                        } else {
//                            bus = new Bus(v1.getVehicleID()); // creating a new bus
//                            buses.put(v1.getVehicleID(), bus);
//                            if (LOGGER.isLoggable(Level.INFO)) {
//                                LOGGER.log(Level.INFO, "New Bus created and added to the map: " + v1.getVehicleID());
//                            }
//                        }
//                    } else if (v1.getVehicleType().equals("train")) {
//                        Train train = trains.get(v1.getPassengerID());
//                        if (train != null) {
//                            train.incrementStopCount(); // Increment train stop count
//                            // trip.setCurrentTrainStop(train);
//                            // train.attachObserver((VehicleObserver) trip);
//                            if (LOGGER.isLoggable(Level.INFO)) {
//                                LOGGER.log(Level.INFO, "Train found and Passenger updated");
//                            } else {
//                                train = new Train(v1.getVehicleID()); // Creating a new Train
//                                trains.put(v1.getVehicleID(), train); // Putting the new Train into the HashMap
//                                if (LOGGER.isLoggable(Level.INFO)) {
//                                    LOGGER.log(Level.INFO, "New Train created and added to the map: " + v1.getVehicleID());
//                                }
//                            }
//                        }
//
//                    }
                    if (event instanceof VehicleLeft) {
                        VehicleLeft v2 = (VehicleLeft) event;

                        if (v1.getVehicleType().equals("BUS")) {
                            Bus bus = buses.get(v2.getVehicleID());
                            if (bus != null) {
                                buses.remove(v2.getVehicleID()); // Removes the bus from the hashmap
                                if (LOGGER.isLoggable(Level.INFO)) {
                                    LOGGER.log(Level.INFO, "Bus removed from the map: " + v1.getVehicleID());
                                } else {
                                    if (LOGGER.isLoggable(Level.WARNING)) {
                                        LOGGER.log(Level.WARNING, "No bus found to remove for the event: " + event.toString());
                                    }
                                }
                            }
                        } else if (v2.getVehicleType().equals("train")) {
                            Train train = trains.get(v2.getVehicleID());
                            if (train != null) {
                                trains.remove(v2.getVehicleID()); // Removes the train from the hashmap
                                if (LOGGER.isLoggable(Level.INFO)) {
                                    LOGGER.log(Level.INFO, "Train removed from the map: " + v1.getVehicleID());
                                } else {
                                    if (LOGGER.isLoggable(Level.WARNING)) {
                                        LOGGER.log(Level.WARNING, "No train found to remove for the event: " + event.toString());
                                    }
                                }
                            }
                        }
                    }

                }
//        try {
//            InputStream inputStream = Main.class.getResourceAsStream("/events.json");
//            JsonParser jsonParser = new JsonParser();
//            JsonElement e = jsonParser.parse(new InputStreamReader(inputStream));
//            JsonArray array = e.getAsJsonArray();
//            System.out.println("String" + array.size());
//            for (int i = 0; i < array.size(); i++) {
//
//
//                Event event = eventFactory.getEvent(array.get(i).getAsJsonObject());
//                if (event instanceof TripStartedEvent) {
//                    TripStartedEvent e1 = (TripStartedEvent) event;
//                    Passenger passenger = passengers.get(e1.getPassengerId());
//                    Trip trip = new Trip(TRIP_ID_PREFIX + (tripIdCount++), e1.getPassengerId(), "v1");
//                    passenger.setCurrentTrip(trip);
//                    trips.put(trip.getTripId(), trip);
//                    trip.attachObserver(passenger);
//                    //vehicle.attachObserver(trip)
//                } else if (event instanceof TripStoppedEvent) {
//                    TripStoppedEvent e1 = (TripStoppedEvent) event;
//                    Passenger passenger = passengers.get(e1.getPassengerId());
//                    Trip trip = passenger.getCurrentTrip();
//                    trip.setTimeEnd(LocalDateTime.now());
//                    trip.setOngoing(false);
//                }
//                else if (event instanceof VehiclePassedStop) {
//                    VehiclePassedStop v1 = (VehiclePassedStop) event;
//                    Passenger passenger = passengers.get(v1.getPassengerID());
//
//                    if (v1.getVehicleType().equals("BUS")) {
//                        Bus bus = buses.get(v1.getVehicleID());
//                        if (bus != null) {
//                            passenger.setCurrentBusStop(bus);
//                            bus.attachObserver((BusObserver) passenger);
//                            logger.log(Level.INFO, "Bus found and Passenger updated");
//                        } else {
//                            System.out.println("Bus not found");
//                            logger.log(Level.WARNING, "Bus not found for the event: " + event.toString());
//                        }
//                    }
//                    else if (v1.getVehicleType().equals("train")) {
//                        Train train = trains.get(TRAIN_ID_PREFIX + (trainStopPassed++) + v1.getPassengerID());
//                        if (train != null) {
//                            passenger.setCurrentTrainStop(train);
//                            train.attachObserver((TrainObserver) passenger);
//                            logger.log(Level.INFO, "Train found and Passenger updated");
//                        } else {
//                            System.out.println("Train not found");
//                            logger.log(Level.WARNING, "Train not found for the event: " + event.toString());
//                        }
//                    }
//                }


//                        if (event instanceof VehicleCount) {
//                            VehicleCount vc1 = (VehicleCount) event;
//                            Passenger passenger1 = passengers.get(vc1.getPassengerId()); // Note: I changed v1 to vc1 as I believe that's what you intended.
//                            if (vc1.getVehicleType().equals("bus")) {
//                                Bus bus = buses.get(BUS_ID_PREFIX + (busCount++) + vc1.getPassengerId());
//                                if (bus != null) {
//                                    passenger1.setCurrentBusCount(bus);
//                                    bus.attachObserver((BusObserver) passenger1);
//                                    logger.log(Level.INFO, "Bus found and Passenger count updated");
//                                }
//                            } else if (vc1.getVehicleType().equals("train")) {
//                                Train train = trains.get(TRAIN_ID_PREFIX + (trainCount++) + vc1.getPassengerId());
//                                if (train != null) {
//                                    passenger1.setCurrentTrainCount(train);
//                                    train.attachObserver((TrainObserver) passenger1);
//                                    logger.log(Level.INFO, "Train found and Passenger count updated");
//                                } else {
//                                    System.out.println("Train not found");
//                                    logger.log(Level.WARNING, "Train not found for the event: " + event.toString());
//                                }
//                            }
//                        }


                try (Scanner scanner = new Scanner(System.in)) {
                    boolean exit = false;
                    while (!exit) {
                        System.out.println("Please choose an option:");
                        System.out.println("1. Get the bus count");
                        System.out.println("2. Get the train count");
                        System.out.println("3. Get the bus stops passed count");
                        System.out.println("4. Get the train stops passed count");
                        System.out.println("5. Get the Account Status");
                        System.out.println("6. Exit");
                        int option = scanner.nextInt();

                        switch (option) {
                            case 1:
                                System.out.println("The bus count is: " + busCount);
                                break;
                            case 2:
                                System.out.println("The train count is: " + trainCount);
                                break;
                            case 3:
                                System.out.println("The bus stops passed count is: ");
                                break;
                            case 4:
                                System.out.println("The train stops passed count is: ");
                                break;
                            case 5:
                                // call the method to get account status
                                break;
                            case 6:
                                exit = true;
                                break;
                            default:
                                System.out.println("Invalid option, please try again.");
                        }
                    }
                } catch (JsonProcessingException e2) {
                    throw new JsonProcessingException("An error occurred",e2);
                }
            }
        } catch (JsonIOException | IOException e) {
            throw new JsonProcessingException("An error occurred while processing JSON input", e);
        } catch (JsonSyntaxException e) {
            throw new JsonProcessingException("An error occurred due to invalid JSON syntax", e);
        } catch (UnknownEventException e) {
            throw new UnknownEventProcessingException("An error occurred due to an unknown event", e);
        }
    }
}








