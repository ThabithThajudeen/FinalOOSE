package com.startransport;
import com.startransport.entities.Passenger;
import com.startransport.events.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Passenger> passengers = new HashMap<>();
       // Map<Integer, Bus> buses = new HashMap<>();
       // Map<Integer, Trains> trains = new HashMap<>();
        Map<String,Trip> trips = new HashMap<>();
        Map<Integer, Event> events = new HashMap<>();

        try {
            Scanner scanner = new Scanner(new File("Passenger.csv"));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");


                //String name = data[0];
                int passengerID = Integer.parseInt(data[0]);
                String name = data[1];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                LocalDateTime paymentRequiredTime = data[2].isEmpty() ? null : LocalDateTime.parse(data[4], formatter);
                LocalDateTime paymentMadeTime = data[3].isEmpty() ? null : LocalDateTime.parse(data[5], formatter);

                org.example.Passenger passenger1 = new org.example.Passenger(passengerID,name,paymentRequiredTime,paymentMadeTime);

                passengers.put(passengerID, passenger1);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Scanner scanner = new Scanner(new File("buses.csv"));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                //int id = Integer.parseInt(data[0]);
                //boolean onTime = Boolean.parseBoolean(data[1]);
                //double latitude = Double.parseDouble(data[2]);
                //double longitude = Double.parseDouble(data[3]);

                int busID = Integer.parseInt(data[0]);
                String status = data[1];
               // Bus.Status accountStatus = Bus.Status.valueOf(data[2].toUpperCase());
                double latitude = Double.parseDouble(data[2]);
                double longitude = Double.parseDouble(data[3]);

                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime plannedArrivalTime = LocalTime.parse(data[4], timeFormatter);
                LocalTime actualArrivalTime = LocalTime.parse(data[5], timeFormatter);
                Bus bus = new Bus(busID, Bus.Status.valueOf(status),latitude,longitude,plannedArrivalTime,actualArrivalTime);
                buses.put(busID, bus);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Scanner scanner = new Scanner(new File("trains.csv"));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                //int id = Integer.parseInt(data[0]);
                //boolean onTime = Boolean.parseBoolean(data[1]);
                //double latitude = Double.parseDouble(data[2]);
                //double longitude = Double.parseDouble(data[3]);

                int TrainID = Integer.parseInt(data[0]);
                Trains.Status accountStatus = Trains.Status.valueOf(data[2].toUpperCase());
                double latitude = Double.parseDouble(data[2]);
                double longitude = Double.parseDouble(data[3]);



                Trains trains1 = new Trains(TrainID, accountStatus, latitude, longitude);
                trains.put(TrainID, trains1);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Scanner scanner = new Scanner(new File("events.csv"));
            scanner.nextLine();
            LocalDateTime previousEventTime = null; // Track the previous event time
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                String eventId = data[0];
                String eventType = data[1];
                String entityId = data[2];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime eventTime ;
                if (previousEventTime != null) {
                    // Calculate the event time assuming 2 minutes after the previous event time
                    eventTime = previousEventTime.plusMinutes(2);
                } else {
                    // Use the first event time as is
                    eventTime = LocalDateTime.parse(data[3], formatter);
                }

                LocalDateTime paymentRequiredTime = data[4].isEmpty() ? null : LocalDateTime.parse(data[4], formatter);
                LocalDateTime paymentMadeTime = data[5].isEmpty() ? null : LocalDateTime.parse(data[5], formatter);
                LocalDateTime scheduledArrivalTime = data[6].isEmpty() ? null : LocalDateTime.parse(data[6],formatter);

                Events event = new Events(eventId, eventType, entityId, eventTime, paymentRequiredTime, paymentMadeTime,scheduledArrivalTime);
                events.put(eventId,event);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Scanner scanner = new Scanner(new File("trips.csv"));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int passengerId = Integer.parseInt(data[0]);
                String tripId = data[1];


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTimeStart = LocalDateTime.parse(data[3], formatter);
                LocalDateTime dateTimeEnd = data[4].isEmpty() ? null : LocalDateTime.parse(data[4], formatter);
                String vehicleId = data[5];
                String vehicleStartLocation = data[6];
                String vehicleEndLocation = data[7];
                org.example.Trip trip1 = new org.example.Trip(passengerId, tripId, dateTimeStart, dateTimeEnd, vehicleId, vehicleStartLocation,vehicleEndLocation);
                trips.put(tripId,trip1);
                org.example.Passenger x = passengers.get(passengerId) ;
                if (x==null)
                {
                    // TODO -> change into proper error classes
                    throw new RuntimeException("Passenger is null for the trip");
                }
                x.addToPastTrips(trip1);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner userInput = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning){
            System.out.println("\n Please select an option");
            System.out.println("1.Search Passenger");
            System.out.println("2.Weather(for instance)passengers's accounts are in good standing,in debt,or cancelled ");
            System.out.println("3.Weather a passengers is currently on board a particular bus/train or not");
            System.out.println("4.The exact times pssengers swipe their credit card(or other card) to enter/leave a bus or train ");
            System.out.println("5.Weather certain buses and trains are on-time,delayed,or cancelled");
            System.out.println("6.The physical location of buses and trains within the transport network");



            int userChoice = userInput.nextInt();
            switch (userChoice){
                case 1:
                    System.out.println("Enter Passenger ID:");
                    int passengerId = userInput.nextInt();
                    org.example.Passenger p = passengers.get(passengerId);
                    if(p != null)
                        System.out.println(p);
                    else
                        System.out.println("No passengers found with ID " + passengerId);
                    break;
                case 2:
                    System.out.println("Enter Passenger ID:");
                    int passengerId02 = userInput.nextInt();
                    org.example.Passenger p2 = passengers.get(passengerId02);
                    if(p2 != null) {
                        System.out.println(p2);
                        System.out.println("Passenger account status: " + p2.getAccountStatus());
                    }
                    else
                        System.out.println("No passengers found with ID " + passengerId02);
                    break;
                case 3:
                    System.out.println("Enter Passenger ID:");
                    int passengerId03 = userInput.nextInt();
                    org.example.Passenger p3 = passengers.get(passengerId03);
                    if(p3 != null) {
                        System.out.println("Passenger onboard status: " + p3.getOnboardStatus());
                        String vehicleId = p3.getCurrentVehicle();
                        if (vehicleId != null)
                            System.out.println("Currently on vehicle: " + vehicleId);
                    }
                    else
                        System.out.println("No passengers found with ID " + passengerId03);
                    break;
                case 4:
                    System.out.println("Enter Passenger ID:");
                    int passengerId04 = userInput.nextInt();
                    Events event = events.get(passengerId04);
                    if (event != null) {
                        LocalDateTime eventTime = event.getEventTime();
                        EventType eventType = event.getEventType();

                        switch (eventType) {
                            case CUSTOMER_ARRIVAL:
                                System.out.println("Swipe-in event at: " + eventTime);
                                break;
                            case CUSTOMER_LEFT:
                                System.out.println("Swipe-out event at: " + eventTime);
                                LocalDateTime paymentRequiredTime = event.getPaymentRequiredTime();
                                LocalDateTime paymentMadeTime = event.getPaymentMadeTime();
                                if (paymentRequiredTime != null && paymentMadeTime != null) {
                                    Duration tripDuration = Duration.between(paymentRequiredTime, paymentMadeTime);
                                    System.out.println("Trip duration: " + tripDuration.toMinutes() + " minutes");
                                }
                                break;
                            default:
                                System.out.println("Invalid event type");
                                break;
                        }
                    } else {
                        System.out.println("No event found for passengers with ID " + passengerId04);
                    }
                    break;

            }
        }
    }
}