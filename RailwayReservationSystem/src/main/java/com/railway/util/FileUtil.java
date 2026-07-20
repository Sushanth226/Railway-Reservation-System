package com.railway.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

/**
 * Utility class for handling file operations and Java Serialization.
 * This class encapsulates the logic for saving and loading data to/from disk.
 */
public class FileUtil {

    /**
     * Saves (serializes) an object to a specified file.
     * 
     * @param data     The object to be saved (e.g., a HashMap of trains).
     * @param fileName The path/name of the file where data will be stored.
     */
    public static void saveData(Object data, String fileName) {
        // Using try-with-resources to automatically close the streams
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeObject(data);
            
        } catch (IOException e) {
            System.err.println("Error saving data to " + fileName + ": " + e.getMessage());
        }
    }

    /**
     * Loads (deserializes) an object from a specified file.
     * 
     * @param fileName The path/name of the file to read from.
     * @return The deserialized Object, or null if the file does not exist or an error occurs.
     */
    public static Object loadData(String fileName) {
        File file = new File(fileName);
        
        // If the file doesn't exist yet (e.g., first time running the app), return null
        if (!file.exists()) {
            return null;
        }

        // Using try-with-resources to automatically close the streams
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            return ois.readObject();
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from " + fileName + ": " + e.getMessage());
            return null;
        }
    }
}
