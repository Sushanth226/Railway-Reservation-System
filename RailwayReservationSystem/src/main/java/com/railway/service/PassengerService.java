package com.railway.service;

import com.railway.model.Passenger;

/**
 * Interface defining the business logic operations for Passenger management.
 */
public interface PassengerService {
    
    /**
     * Registers a new passenger in the system and automatically generates a Passenger ID.
     * 
     * @param name   The name of the passenger
     * @param age    The age of the passenger
     * @param gender The gender of the passenger
     * @return The newly created Passenger object
     */
    Passenger registerPassenger(String name, int age, String gender);

    /**
     * Retrieves a passenger by their unique ID.
     * 
     * @param passengerId The ID of the passenger to search for
     * @return The Passenger object if found, or null if not found
     */
    Passenger getPassengerById(int passengerId);
    
    /**
     * Forces the service to save its current passenger data to the file.
     */
    void savePassengerData();
}
