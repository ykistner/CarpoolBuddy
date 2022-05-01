package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Car extends Vehicle{
    private int range;

    public Car(String owner, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, int range) {
        super(owner, model, capacity, vehicleID, ridersUIDs, open, vehicleType, basePrice);
        this.range = range;
    }

    public Car(String ownerString, String modelString, int capacityInt, String vehicleId, double basePriceDouble) {
    }

    public Car(String uid, String ownerString, String capacityString, String modelString, String basePriceString, int rangeInt) {
    }

    public Car(String uid, String ownerString, String capacityString, String modelString, String basePriceString, int rangeInt, double weightCapacityDouble) {
    }

    public Car(String uid, String ownerString, String capacityString, String modelString, String basePriceString, int weight, String bicycleTypeString, double weightCapacityDouble) {
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "Car{" +
                "range=" + range +
                '}';
    }
}
