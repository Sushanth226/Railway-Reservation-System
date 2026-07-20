package com.railway.service;

import com.railway.model.Ticket;
import com.railway.exception.InvalidPNRException;

import java.util.List;

/**
 * Interface defining the business logic operations for Ticket Booking and Management.
 */
public interface BookingService {

    /**
     * Books a ticket for a passenger on a specific train.
     * 
     * @param passengerId The ID of the passenger making the booking.
     * @param trainNumber The number of the train to book.
     * @return The generated Ticket object containing the PNR and allocated seat.
     * @throws Exception If the passenger is not found, train is not found, or seats are full.
     */
    Ticket bookTicket(int passengerId, int trainNumber) throws Exception;

    /**
     * Cancels an existing ticket using its PNR.
     * 
     * @param pnr The Passenger Name Record of the ticket to cancel.
     * @throws InvalidPNRException If the PNR does not exist in the system.
     */
    void cancelTicket(String pnr) throws InvalidPNRException;

    /**
     * Searches for a ticket using its PNR.
     * 
     * @param pnr The Passenger Name Record to search for.
     * @return The Ticket object.
     * @throws InvalidPNRException If the PNR does not exist in the system.
     */
    Ticket searchTicket(String pnr) throws InvalidPNRException;

    /**
     * Retrieves a list of all tickets in the system.
     * 
     * @return A List of all Ticket objects.
     */
    List<Ticket> viewAllTickets();

    /**
     * Forces the service to save its current ticket data to the file.
     */
    void saveTicketData();
}
