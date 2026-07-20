package com.railway.main;

import com.railway.exception.InvalidPNRException;
import com.railway.exception.TrainNotFoundException;
import com.railway.model.Passenger;
import com.railway.model.Ticket;
import com.railway.model.Train;
import com.railway.service.BookingService;
import com.railway.service.PassengerService;
import com.railway.service.TrainService;
import com.railway.service.impl.BookingServiceImpl;
import com.railway.service.impl.PassengerServiceImpl;
import com.railway.service.impl.TrainServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class for the Railway Reservation System.
 * Acts as the UI (User Interface) interacting with the business services.
 */
public class RailwayReservationApp {

    // Instantiate Services using Interface references (Programming to an Interface)
    private static PassengerService passengerService = new PassengerServiceImpl();
    private static TrainService trainService = new TrainServiceImpl();
    // Dependency Injection: Pass the required services into the BookingService
    private static BookingService bookingService = new BookingServiceImpl(passengerService, trainService);

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   Welcome to the Railway Reservation System     ");
        System.out.println("=================================================");

        boolean exit = false;

        while (!exit) {
            printMenu();
            int choice = -1;

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character left by nextInt()

                switch (choice) {
                    case 1:
                        registerPassenger();
                        break;
                    case 2:
                        addTrain();
                        break;
                    case 3:
                        viewAllTrains();
                        break;
                    case 4:
                        searchTrain();
                        break;
                    case 5:
                        bookTicket();
                        break;
                    case 6:
                        cancelTicket();
                        break;
                    case 7:
                        searchTicket();
                        break;
                    case 8:
                        viewAllTickets();
                        break;
                    case 9:
                        saveAllData();
                        break;
                    case 10:
                        System.out.println("Saving data and exiting... Thank you!");
                        saveAllData();
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a number between 1 and 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input from scanner buffer
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
            System.out.println(); // Print a blank line for readability
        }
        
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("-------------------------------------------------");
        System.out.println("1. Register Passenger");
        System.out.println("2. Add Train");
        System.out.println("3. View Trains");
        System.out.println("4. Search Train");
        System.out.println("5. Book Ticket");
        System.out.println("6. Cancel Ticket");
        System.out.println("7. Search Ticket");
        System.out.println("8. View All Tickets");
        System.out.println("9. Save Data (Manual)");
        System.out.println("10. Exit");
        System.out.println("-------------------------------------------------");
        System.out.print("Enter your choice: ");
    }

    private static void registerPassenger() {
        System.out.print("Enter Passenger Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter Gender (M/F/Other): ");
        String gender = scanner.nextLine();

        Passenger passenger = passengerService.registerPassenger(name, age, gender);
        System.out.println("Success! Passenger registered with ID: " + passenger.getPassengerId());
    }

    private static void addTrain() {
        System.out.print("Enter Train Number (e.g., 12001): ");
        int trainNo = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter Train Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Source Station: ");
        String source = scanner.nextLine();
        
        System.out.print("Enter Destination Station: ");
        String destination = scanner.nextLine();
        
        System.out.print("Enter Total Seats: ");
        int totalSeats = scanner.nextInt();
        
        System.out.print("Enter Ticket Price (e.g., 1200.50): ");
        double price = scanner.nextDouble();

        Train train = new Train(trainNo, name, source, destination, totalSeats, totalSeats, price);
        trainService.addTrain(train);
        
        System.out.println("Success! Train added successfully.");
    }

    private static void viewAllTrains() {
        List<Train> trains = trainService.viewAllTrains();
        if (trains.isEmpty()) {
            System.out.println("No trains available in the system.");
        } else {
            System.out.println("--- Available Trains ---");
            for (Train t : trains) {
                System.out.println(t);
            }
        }
    }

    private static void searchTrain() {
        System.out.print("Enter Train Number to search: ");
        int trainNo = scanner.nextInt();
        
        try {
            Train train = trainService.searchTrainByNumber(trainNo);
            System.out.println("Train Found: " + train);
        } catch (TrainNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void bookTicket() {
        System.out.print("Enter Passenger ID: ");
        int passengerId = scanner.nextInt();
        
        System.out.print("Enter Train Number: ");
        int trainNo = scanner.nextInt();
        
        try {
            Ticket ticket = bookingService.bookTicket(passengerId, trainNo);
            System.out.println("Ticket Booked Successfully!");
            System.out.println(ticket);
        } catch (Exception e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }

    private static void cancelTicket() {
        System.out.print("Enter PNR Number to cancel: ");
        String pnr = scanner.nextLine();
        
        try {
            bookingService.cancelTicket(pnr);
            System.out.println("Ticket with PNR '" + pnr + "' has been successfully cancelled.");
        } catch (InvalidPNRException e) {
            System.out.println("Cancellation Error: " + e.getMessage());
        }
    }

    private static void searchTicket() {
        System.out.print("Enter PNR Number to search: ");
        String pnr = scanner.nextLine();
        
        try {
            Ticket ticket = bookingService.searchTicket(pnr);
            System.out.println("Ticket Details: " + ticket);
        } catch (InvalidPNRException e) {
            System.out.println("Search Error: " + e.getMessage());
        }
    }

    private static void viewAllTickets() {
        List<Ticket> tickets = bookingService.viewAllTickets();
        if (tickets.isEmpty()) {
            System.out.println("No tickets have been booked yet.");
        } else {
            System.out.println("--- All Tickets ---");
            for (Ticket t : tickets) {
                System.out.println(t);
            }
        }
    }

    private static void saveAllData() {
        passengerService.savePassengerData();
        trainService.saveTrainData();
        bookingService.saveTicketData();
        System.out.println("All data saved to disk successfully.");
    }
}
