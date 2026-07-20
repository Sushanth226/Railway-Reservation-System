package com.railway.exception;

/**
 * Custom exception thrown when a requested PNR (Passenger Name Record) is invalid or not found.
 */
public class InvalidPNRException extends Exception {

    /**
     * Constructs a new InvalidPNRException with the specified detail message.
     * 
     * @param message the detail message explaining why the exception was thrown.
     */
    public InvalidPNRException(String message) {
        super(message);
    }
}
