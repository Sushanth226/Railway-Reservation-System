package com.railway.exception;

/**
 * Custom exception thrown when a requested train cannot be found in the system.
 */
public class TrainNotFoundException extends Exception {

    /**
     * Constructs a new TrainNotFoundException with the specified detail message.
     * 
     * @param message the detail message explaining why the exception was thrown.
     */
    public TrainNotFoundException(String message) {
        super(message);
    }
}
