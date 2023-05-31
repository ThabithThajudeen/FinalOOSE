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
    private static final String BUS_ID_PREFIX = "BUS-";
    //  private static final String PASSENGER_ID_PREFIX = "PASSENGER-";
    // private static final String EVENT_ID_PREFIX = "EVENT-";
    private static int tripIdCount = 1;
    //private static int eventIdCount = 1;
     private static int busIdCount = 1;
     //private static int trainIdCount = 1;
     //private static int busCount = 1;
    //private static int trainCount = 1;
    static private EventFactory eventFactory = new EventFactory();
    private static Map<String, Passenger> passengers = new HashMap<>();
    //private static Map<String, Bus> buses = new HashMap<>();
    //private static Map<String, Train> trains = new HashMap<>();
    private static Map<String, Vehicle> vehicles = new HashMap<>();

    private static Map<String, Trip> trips = new HashMap<>();
    //private static Map<String, Event> events = new HashMap<>();

    public static void main(String[] args) throws UnknownEventException, InterruptedException {
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
               // String busID = data[0];
              //  String passengerID = data[1];
                int totalSeatCount = Integer.parseInt(data[1]);


                // Create a new bus with the read bus ID
                Vehicle vehicle = new Vehicle(BUS_ID_PREFIX + (busIdCount++) ,totalSeatCount,totalSeatCount,VehicleType.BUS);
                vehicles.put(vehicle.getVehicleID(), vehicle);

            }
            LOGGER.log(Level.INFO, "Buses loaded successfully");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while loading buses", e);
            throw new UnknownEventException();
        }

        LOGGER.log(Level.INFO, "bus reading and loaded ended");
        System.out.println("Hii04");

        try (FileReader reader = new FileReader("src/main/resources/Trains.csv");
             Scanner scanner = new Scanner(reader)) {

            LOGGER.log(Level.INFO, "Bus reading has started");
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");

                ;
                int totalSeatCount = Integer.parseInt(data[1]);


                // Create a new bus with the read bus ID
                Vehicle vehicle = new Vehicle(BUS_ID_PREFIX + (busIdCount++) ,totalSeatCount,totalSeatCount,VehicleType.TRAINS);
                vehicles.put(vehicle.getVehicleID(), vehicle);

            }
            LOGGER.log(Level.INFO, "Trains loaded successfully");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while loading Trains", e);
            throw new UnknownEventException();
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
                    Vehicle v = vehicles.get(e1.getVehicleID());
                    if (v != null) {
                        v.attachObserver(trip);
                        trip.setInitialStopCount(v.getCurrentStopCount());
                    }

                } else if (event instanceof TripStoppedEvent) {
                    TripStoppedEvent e1 = (TripStoppedEvent) event;
                    Passenger passenger = passengers.get(e1.getPassengerId());
                    Trip trip = passenger.getCurrentTrip();
                    trip.setTimeEnd(LocalDateTime.now());
                    trip.setOngoing(false);
                }
                else if (event instanceof VehiclePassedStop) {
                    VehiclePassedStop v1 = (VehiclePassedStop) event;
                    //  Passenger passenger = passengers.get(v1.getPassengerID());
                //    Trip trip = trips.get(v1.getPassengerID());
                    Vehicle v = vehicles.get(v1.getVehicleID());
                    if (v != null) {
                        //v.attachObserver(trip);
                        v.incrementStopCount();
                        //trip.setInitialStopCount(v.getCurrentStopCount());
                    }

                }else if (event instanceof VehicleLeft) {
                    VehicleLeft v2 = (VehicleLeft) event;
                    Vehicle v = vehicles.get(v2.getVehicleID());
                    if (v != null) {
                        vehicles.remove(v2.getVehicleID());
                    } else {
                        if (LOGGER.isLoggable(Level.WARNING)) {
                            LOGGER.log(Level.WARNING, "Vehicle not found: " + event.toString());
                        }
                    }

                }
                if (event instanceof CardSwiped) {
                    CardSwiped e1 = (CardSwiped) event;
                    Passenger passenger = passengers.get(e1.getPassengerId());

                    if (passenger != null) {
                        Trip trip = passenger.getCurrentTrip();
                        if (trip != null) {
                            trip.setCardSwiped(true);
                        }

                    }
                }
            }
        } catch (JsonIOException | IOException e) {
            throw new JsonProcessingException("An error occurred while processing JSON input", e);
        } catch (JsonSyntaxException e) {
            throw new JsonProcessingException("An error occurred due to invalid JSON syntax", e);
        } catch (UnknownEventException e) {
            throw new UnknownEventProcessingException("An error occurred due to an unknown event", e);
        }
        Thread.sleep(2000);
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            while (!exit) {
                System.out.println("Please choose an option:");
                System.out.println("1. Get the bus count");
                System.out.println("2. Get the train count");
                System.out.println("3. Get the bus information");
                System.out.println("4. Get the train information");
                System.out.println("5. Get the Account Status");
                System.out.println("6. Exit");
                int option = scanner.nextInt();
                scanner.nextLine();
                //passenger info,-> passengerr account info -> total payable,
                //passenger information also passenger current information
                switch (option) {
                    case 1:
                        System.out.println("The bus count is: " + vehicles.values()
                                .stream().filter(v -> v.getVehicleType() == VehicleType.BUS).count());
                        break;
                    case 2:
                        System.out.println("The train count is: " + vehicles.values()
                                .stream().filter(v -> v.getVehicleType() == VehicleType.TRAINS).count());
                        break;
                    case 3:
                        System.out.println(" Enter bus ID to get bus information: ");
                        String busID = scanner.nextLine().trim();
                        Vehicle v = vehicles.get(busID);
                        if (v != null) {
                            System.out.println("Vehicle ID" + v.getVehicleID());
                            System.out.println("Available Seat Count" + v.hasAvailableSeats());
                            System.out.println("Total Seat Count" + v.getTotalSeatCount());
                            System.out.println("Ongoing Trips Count" + v.getOngoingTripCount());

                        } else {
                            System.out.println("Bus not found for " + busID);
                        }
                        break;
                    case 4:
                        System.out.println(" Enter train ID to get bus information: ");
                        String trainID = scanner.nextLine().trim();
                        Vehicle v2 = vehicles.get(trainID);
                        if (v2 != null) {
                            System.out.println("Vehicle ID" + v2.getVehicleID());
                            System.out.println("Available Seat Count" + v2.hasAvailableSeats());
                            System.out.println("Total Seat Count" + v2.getTotalSeatCount());
                            System.out.println("Ongoing Trips Count" + v2.getOngoingTripCount());

                        } else {
                            System.out.println("Bus not found for " + trainID);
                        }

                        break;
                    case 5:
                        System.out.println("Enter passenger ID to get information: ");
                        String passengerID = scanner.nextLine().trim();
                        Passenger passenger = passengers.get(passengerID);
                        if (passenger != null) {
                            System.out.println("Passenger ID: " + passenger.getPassengerID());
                            System.out.println("Passenger Name: " + passenger.getPassengerName());
                            Trip currentTrip = passenger.getCurrentTrip();
                            if (currentTrip != null) {
                                System.out.println("Current Trip ID: " + currentTrip.getTripId());
                                double calculateFare = currentTrip.calculateFare();
                                System.out.println("Total Payable Amount: " + calculateFare);
                            } else {
                                System.out.println("No current trip for this passenger.");
                            }
                        } else {
                            System.out.println("Passenger not found for ID: " + passengerID);
                        }


                        break;
                    case 6:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            }
        } catch (JsonProcessingException e2) {
            throw new JsonProcessingException("An error occurred", e2);
        }
    }
}








