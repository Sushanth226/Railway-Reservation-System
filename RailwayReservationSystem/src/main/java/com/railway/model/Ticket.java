package com.railway.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a booked ticket in the Railway Reservation System.
 * Implements Serializable to allow saving/loading ticket data to/from a file.
 */
public class Ticket implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String pnr;
    private Passenger passenger;
    private Train train;
    private LocalDate travelDate;
    private int seatNumber;
    private double fare;
    private TicketStatus status;

    /**
     * Default constructor.
     */
    public Ticket() {
    }

    /**
     * Parameterized constructor to initialize a new ticket.
     * 
     * @param pnr        Unique Passenger Name Record
     * @param passenger  The passenger who booked the ticket
     * @param train      The train for which the ticket is booked
     * @param travelDate Date of travel
     * @param seatNumber Allocated seat number
     * @param fare       Total ticket fare
     * @param status     Current status of the ticket (BOOKED or CANCELLED)
     */
    public Ticket(String pnr, Passenger passenger, Train train, LocalDate travelDate, int seatNumber, double fare, TicketStatus status) {
        this.pnr = pnr;
        this.passenger = passenger;
        this.train = train;
        this.travelDate = travelDate;
        this.seatNumber = seatNumber;
        this.fare = fare;
        this.status = status;
    }

    // Getters and Setters

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket [" +
                "PNR='" + pnr + '\'' +
                ", Passenger=" + passenger.getName() +
                ", Train=" + train.getTrainName() + " (" + train.getTrainNumber() + ")" +
                ", Travel Date=" + travelDate +
                ", Seat No=" + seatNumber +
                ", Fare=₹" + fare +
                ", Status=" + status +
                ']';
    }
}
