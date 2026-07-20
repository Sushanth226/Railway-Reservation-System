package com.railway.model;

import java.io.Serializable;

/**
 * Represents a Train in the Railway Reservation System.
 * Implements Serializable to allow saving/loading train data to/from a file.
 */
public class Train implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private int trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private double ticketPrice;

    /**
     * Default constructor.
     */
    public Train() {
    }

    /**
     * Parameterized constructor to initialize a new train.
     * 
     * @param trainNumber    Unique number of the train
     * @param trainName      Name of the train
     * @param source         Starting station
     * @param destination    Ending station
     * @param totalSeats     Total capacity of the train
     * @param availableSeats Number of seats currently available
     * @param ticketPrice    Price of a single ticket
     */
    public Train(int trainNumber, String trainName, String source, String destination, int totalSeats, int availableSeats, double ticketPrice) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
    }

    // Getters and Setters

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Train [" +
                "Train Number=" + trainNumber +
                ", Name='" + trainName + '\'' +
                ", Route='" + source + " -> " + destination + '\'' +
                ", Seats (Available/Total)=" + availableSeats + "/" + totalSeats +
                ", Price=₹" + ticketPrice +
                ']';
    }
}
