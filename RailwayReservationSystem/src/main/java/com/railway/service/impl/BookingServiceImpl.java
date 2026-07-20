package com.railway.service.impl;

import com.railway.model.Passenger;
import com.railway.model.Ticket;
import com.railway.model.TicketStatus;
import com.railway.model.Train;
import com.railway.service.BookingService;
import com.railway.service.PassengerService;
import com.railway.service.TrainService;
import com.railway.util.FileUtil;
import com.railway.exception.InvalidPNRException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the BookingService interface.
 * Handles complex logic involving linking passengers, trains, and tickets.
 */
public class BookingServiceImpl implements BookingService {

    // Internal storage for tickets. Key = PNR (String), Value = Ticket object.
    private Map<String, Ticket> ticketMap;
    private static final String FILE_NAME = "data/tickets.dat";

    // Dependencies: BookingService needs to talk to Passenger and Train services
    private PassengerService passengerService;
    private TrainService trainService;

    /**
     * Constructor.
     * We pass the other services via the constructor (Dependency Injection).
     * 
     * @param passengerService The passenger service instance
     * @param trainService     The train service instance
     */
    @SuppressWarnings("unchecked")
    public BookingServiceImpl(PassengerService passengerService, TrainService trainService) {
        this.passengerService = passengerService;
        this.trainService = trainService;

        Object data = FileUtil.loadData(FILE_NAME);
        if (data != null && data instanceof Map) {
            ticketMap = (Map<String, Ticket>) data;
        } else {
            ticketMap = new HashMap<>();
        }
    }

    @Override
    public Ticket bookTicket(int passengerId, int trainNumber) throws Exception {
        // 1. Verify passenger exists
        Passenger passenger = passengerService.getPassengerById(passengerId);
        if (passenger == null) {
            throw new Exception("Passenger with ID " + passengerId + " not found. Please register first.");
        }

        // 2. Verify train exists (searchTrainByNumber throws TrainNotFoundException if not found)
        Train train = trainService.searchTrainByNumber(trainNumber);

        // 3. Check seat availability
        if (train.getAvailableSeats() <= 0) {
            throw new Exception("Sorry, Train " + trainNumber + " is fully booked.");
        }

        // 4. Allocate seat and reduce availability
        int allocatedSeat = train.getTotalSeats() - train.getAvailableSeats() + 1;
        train.setAvailableSeats(train.getAvailableSeats() - 1);
        
        // Save the updated train state to disk!
        trainService.saveTrainData();

        // 5. Generate PNR (Simple generation: PNR + current milliseconds for uniqueness)
        String pnr = "PNR" + System.currentTimeMillis();

        // 6. Create the ticket (assume travel date is tomorrow for simplicity)
        Ticket ticket = new Ticket(
                pnr, 
                passenger, 
                train, 
                LocalDate.now().plusDays(1), 
                allocatedSeat, 
                train.getTicketPrice(), 
                TicketStatus.BOOKED
        );

        // 7. Save to map and disk
        ticketMap.put(pnr, ticket);
        saveTicketData();

        return ticket;
    }

    @Override
    public void cancelTicket(String pnr) throws InvalidPNRException {
        // 1. Find ticket
        Ticket ticket = ticketMap.get(pnr);
        if (ticket == null) {
            throw new InvalidPNRException("Cancellation failed: No ticket found with PNR " + pnr);
        }

        // 2. Check if already cancelled
        if (ticket.getStatus() == TicketStatus.CANCELLED) {
            throw new InvalidPNRException("This ticket is already cancelled.");
        }

        // 3. Mark as cancelled
        ticket.setStatus(TicketStatus.CANCELLED);

        try {
            // 4. Increase train seat availability
            // We fetch the live train object from the TrainService to ensure data consistency
            Train liveTrain = trainService.searchTrainByNumber(ticket.getTrain().getTrainNumber());
            liveTrain.setAvailableSeats(liveTrain.getAvailableSeats() + 1);
            trainService.saveTrainData();
        } catch (Exception e) {
            System.err.println("Warning: Could not update train seat count during cancellation.");
        }

        // 5. Save updated ticket to disk
        saveTicketData();
    }

    @Override
    public Ticket searchTicket(String pnr) throws InvalidPNRException {
        Ticket ticket = ticketMap.get(pnr);
        if (ticket == null) {
            throw new InvalidPNRException("Search failed: No ticket found with PNR " + pnr);
        }
        return ticket;
    }

    @Override
    public List<Ticket> viewAllTickets() {
        return new ArrayList<>(ticketMap.values());
    }

    @Override
    public void saveTicketData() {
        FileUtil.saveData(ticketMap, FILE_NAME);
    }
}
