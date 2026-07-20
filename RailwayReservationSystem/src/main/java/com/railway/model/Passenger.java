package com.railway.model;

import java.io.Serializable;

/**
 * Represents a passenger in the Railway Reservation System.
 * This class must implement Serializable so that we can save and load passenger data to/from a file.
 */
public class Passenger implements Serializable {
    
    // A unique identifier for serialization. Good practice to avoid InvalidClassException.
    private static final long serialVersionUID = 1L;

    private int passengerId;
    private String name;
    private int age;
    private String gender;

    /**
     * Default constructor.
     * Required for some frameworks and general flexibility, though we might not use it directly here.
     */
    public Passenger() {
    }

    /**
     * Parameterized constructor to initialize a new passenger.
     * 
     * @param passengerId Unique ID for the passenger
     * @param name        Name of the passenger
     * @param age         Age of the passenger
     * @param gender      Gender of the passenger
     */
    public Passenger(int passengerId, String name, int age, String gender) {
        this.passengerId = passengerId;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // Getters and Setters

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Overriding toString() to provide a readable string representation of the object.
     * Helpful for debugging and printing to the console.
     */
    @Override
    public String toString() {
        return "Passenger [" +
                "ID=" + passengerId +
                ", Name='" + name + '\'' +
                ", Age=" + age +
                ", Gender='" + gender + '\'' +
                ']';
    }
}
