package com.railway.service.impl;

import com.railway.model.Train;
import com.railway.service.TrainService;
import com.railway.util.FileUtil;
import com.railway.exception.TrainNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the TrainService interface.
 * Manages adding, retrieving, and storing train data.
 */
public class TrainServiceImpl implements TrainService {

    // Internal storage for trains. Key = Train Number, Value = Train object.
    private Map<Integer, Train> trainMap;
    
    private static final String FILE_NAME = "data/trains.dat";

    /**
     * Constructor to initialize the service and load existing data.
     */
    @SuppressWarnings("unchecked")
    public TrainServiceImpl() {
        Object data = FileUtil.loadData(FILE_NAME);
        if (data != null && data instanceof Map) {
            trainMap = (Map<Integer, Train>) data;
        } else {
            trainMap = new HashMap<>();
        }
    }

    @Override
    public void addTrain(Train train) {
        // The train object is already fully populated by the administrator/menu
        trainMap.put(train.getTrainNumber(), train);
        
        // Save immediately to ensure persistence
        saveTrainData();
    }

    @Override
    public List<Train> viewAllTrains() {
        // Convert the values of the HashMap into a standard ArrayList and return it
        return new ArrayList<>(trainMap.values());
    }

    @Override
    public Train searchTrainByNumber(int trainNumber) throws TrainNotFoundException {
        Train train = trainMap.get(trainNumber);
        
        // If the HashMap returns null, the train does not exist
        if (train == null) {
            throw new TrainNotFoundException("Sorry, no train found with number: " + trainNumber);
        }
        
        return train;
    }

    @Override
    public void saveTrainData() {
        FileUtil.saveData(trainMap, FILE_NAME);
    }
}
