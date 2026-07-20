package com.railway.service.impl;

import com.railway.model.Passenger;
import com.railway.service.PassengerService;
import com.railway.util.FileUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the PassengerService interface.
 * Handles the actual business logic and data storage for passengers.
 */
public class PassengerServiceImpl implements PassengerService {

    // The collection used to store passengers in memory. Key = Passenger ID, Value = Passenger object.
    private Map<Integer, Passenger> passengerMap;
    
    // File path where passenger data will be serialized.
    private static final String FILE_NAME = "data/passengers.dat";

    /**
     * Constructor.
     * When the service is instantiated, it automatically tries to load existing data from the file.
     */
    @SuppressWarnings("unchecked")
    public PassengerServiceImpl() {
        Object data = FileUtil.loadData(FILE_NAME);
        if (data != null && data instanceof Map) {
            passengerMap = (Map<Integer, Passenger>) data;
        } else {
            // If file doesn't exist or is empty, initialize a new HashMap
            passengerMap = new HashMap<>();
        }
    }

    @Override
    public Passenger registerPassenger(String name, int age, String gender) {
        // Auto-generate ID: Simple logic for a student project (max key + 1)
        int newId = 1;
        for (Integer id : passengerMap.keySet()) {
            if (id >= newId) {
                newId = id + 1;
            }
        }

        Passenger newPassenger = new Passenger(newId, name, age, gender);
        
        // Add to map
        passengerMap.put(newId, newPassenger);
        
        // Save to file immediately after modification
        savePassengerData();
        
        return newPassenger;
    }

    @Override
    public Passenger getPassengerById(int passengerId) {
        // Returns the passenger if found, otherwise returns null
        return passengerMap.get(passengerId);
    }

    @Override
    public void savePassengerData() {
        FileUtil.saveData(passengerMap, FILE_NAME);
    }
}
