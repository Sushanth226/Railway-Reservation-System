package com.railway.service;

import com.railway.model.Train;
import com.railway.exception.TrainNotFoundException;

import java.util.List;

/**
 * Interface defining the business logic operations for Train management.
 */
public interface TrainService {

    /**
     * Adds a new train to the system.
     * 
     * @param train The fully populated Train object to add.
     */
    void addTrain(Train train);

    /**
     * Retrieves a list of all trains available in the system.
     * 
     * @return A List of Train objects.
     */
    List<Train> viewAllTrains();

    /**
     * Searches for a train using its unique train number.
     * 
     * @param trainNumber The train number to search for (e.g., 12001).
     * @return The Train object if found.
     * @throws TrainNotFoundException If no train matches the provided train number.
     */
    Train searchTrainByNumber(int trainNumber) throws TrainNotFoundException;

    /**
     * Forces the service to save its current train data to the file.
     */
    void saveTrainData();
}
