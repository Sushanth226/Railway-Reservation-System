import com.railway.model.*;
import com.railway.service.*;
import com.railway.service.impl.*;

import java.util.List;

public class Verify {
    public static void main(String[] args) throws Exception {
        PassengerService passengerService = new PassengerServiceImpl();
        TrainService trainService = new TrainServiceImpl();
        BookingService bookingService = new BookingServiceImpl(passengerService, trainService);

        // Register passengers
        Passenger p1 = passengerService.registerPassenger("Alice", 25, "F");
        Passenger p2 = passengerService.registerPassenger("Bob", 30, "M");
        Passenger p3 = passengerService.registerPassenger("Charlie", 35, "M");

        // Add a train with ONLY 2 seats
        Train train = new Train(1001, "Test Express", "A", "B", 2, 2, 100.0);
        trainService.addTrain(train);

        System.out.println("Booking Ticket 1 (Should be base price 100)...");
        Ticket t1 = bookingService.bookTicket(p1.getPassengerId(), 1001);
        System.out.println("T1 Price: " + t1.getFare() + " Status: " + t1.getStatus());

        System.out.println("Booking Ticket 2 (Should be surge price 150 since 1 seat left <= 10% of 2)...");
        Ticket t2 = bookingService.bookTicket(p2.getPassengerId(), 1001);
        System.out.println("T2 Price: " + t2.getFare() + " Status: " + t2.getStatus());

        System.out.println("Booking Ticket 3 (Should be Waitlisted, price 150)...");
        Ticket t3 = bookingService.bookTicket(p3.getPassengerId(), 1001);
        System.out.println("T3 Price: " + t3.getFare() + " Status: " + t3.getStatus());
        
        System.out.println("Waitlist size before cancel: " + trainService.searchTrainByNumber(1001).getWaitingList().size());

        System.out.println("Cancelling Ticket 1...");
        bookingService.cancelTicket(t1.getPnr());

        System.out.println("Checking T3 Status (Should be upgraded to BOOKED)...");
        Ticket t3Updated = bookingService.searchTicket(t3.getPnr());
        System.out.println("T3 Status: " + t3Updated.getStatus() + " Seat: " + t3Updated.getSeatNumber());
        
        System.out.println("Waitlist size after cancel: " + trainService.searchTrainByNumber(1001).getWaitingList().size());
        System.out.println("Available seats: " + trainService.searchTrainByNumber(1001).getAvailableSeats());
    }
}
