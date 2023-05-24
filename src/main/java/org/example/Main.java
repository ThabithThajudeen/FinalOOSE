package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PseudoColumnUsage;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Passengers> passenger = new HashMap<>();
        Map<Integer, Bus> buses = new HashMap<>();
        Map<Integer, Trains> trains = new HashMap<>();
        Map<Integer, Trips> trips = new HashMap<>();
        Map<Integer, Events> events = new HashMap<>();

        try {
            Scanner scanner = new Scanner(new File("Passenger.csv"));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");


                //String name = data[0];
                int passengerID = Integer.parseInt(data[0]);
                String name = data[0];
                Passengers.AccountStatus accountStatus = Passengers.AccountStatus.valueOf(data[2].toUpperCase());

                Passengers passenger1 = new Passengers(passengerID,name,accountStatus);

                passenger.put(passengerID, passenger1);
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
                Bus.Status accountStatus = Bus.Status.valueOf(data[2].toUpperCase());
                double latitude = Double.parseDouble(data[2]);
                double longitude = Double.parseDouble(data[3]);



                Bus bus = new Bus(busID, accountStatus, latitude, longitude);
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
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                String eventId = data[0];
                String eventType = data[1];
                String entityId = data[2];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime eventTime = LocalDateTime.parse(data[3], formatter);
                LocalDateTime paymentRequiredTime = data[4].isEmpty() ? null : LocalDateTime.parse(data[4], formatter);
                LocalDateTime paymentMadeTime = data[5].isEmpty() ? null : LocalDateTime.parse(data[5], formatter);
                LocalDateTime scheduledArrivalTime = data[6].isEmpty() ? null : LocalDateTime.parse(data[6],formatter);

                Events event = new Events(eventId, eventType, entityId, eventTime, paymentRequiredTime, paymentMadeTime,scheduledArrivalTime);
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
               // String entityId = data[2];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTimeStart = LocalDateTime.parse(data[3], formatter);
                LocalDateTime dateTimeEnd = data[4].isEmpty() ? null : LocalDateTime.parse(data[4], formatter);
               // LocalDateTime paymentMadeTime = data[5].isEmpty() ? null : LocalDateTime.parse(data[5], formatter);
                //LocalDateTime scheduledArrivalTime = data[6].isEmpty() ? null : LocalDateTime.parse(data[6],formatter);
                String vehicleId = data[5];
                String vehicleStartLocation = data[6];
                String vehicleEndLocation = data[7];
                Trips trips1 = new Trips(passengerId, tripId, dateTimeStart, dateTimeEnd, vehicleId, vehicleStartLocation,vehicleEndLocation);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner userInput = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning){
            System.out.println("\n Please select an option");
            System.out.println("1.Search Passengers");

            int userChoice = userInput.nextInt();
            switch (userChoice){
                case 1:
                    System.out.println("Enter Passenger ID:");
                    int passengerId = userInput.nextInt();
                    Passengers p = passenger.get(passengerId);
                    if(p != null)
                        System.out.println(p);
                    else
                        System.out.println("No passenger found with ID " + passengerId);
                    break;
                    case 2:
                    System.out.println("Enter Passenger ID:");
                    int passengerId02 = userInput.nextInt();
                    Passengers p2 = passenger.get(passengerId02);

                    if (p2 != null) {
                        // Assuming that the passengerId is the same as entityId in events
                        // And only one event is associated with each passenger
                        Events e = events.get(passengerId02);

                        if (e != null) {
                            LocalDateTime paymentRequiredTime = e.getPaymentRequiredTime();
                            LocalDateTime paymentMadeTime = e.getPaymentMadeTime();

                            AccountState state;
                            if (paymentMadeTime == null) {
                                state = new CancelledState();
                            } else if (paymentMadeTime.isBefore(paymentRequiredTime)) {
                                state = new InDebtState();
                            } else {
                                state = new GoodStandingState();
                            }

                            System.out.println(state.getStatusMessage());
                        } else {
                            System.out.println("No events found for Passenger ID " + passengerId02);
                        }
                    } else {
                        System.out.println("No passenger found with ID " + passengerId02);
                    }
                    break;
            }
        }
    }
}