package com.example.carpoolbuddy;

public class Car extends Vehicle{
    private int range;

    public Car(String owner, String model, double basePrice, int range, int capacity) {
        super(owner, model, basePrice, capacity);
        this.range = range;
    }

    public Car(String uid, String string, int ownerString, double modelString, String priceDouble, int capacityInt, String vehicleId, double basePriceDouble) {
    }

    public Car(String uid, String ownerString, String capacityString, String modelString, String basePriceString, int rangeInt) {
    }

    public Car(String uid, String ownerString, String capacityString, String modelString, String basePriceString, int rangeInt, double weightCapacityDouble) {
    }

    public Car(String uid, String ownerString, String capacityString, String modelString, String basePriceString, int weight, String bicycleTypeString, double weightCapacityDouble) {
    }

    public Car(String uid, String ownerString, String capacityString, String modelString, double basePriceDouble, int rangeInt, double weightCapacityDouble) {
    }

    public Car(String ownerString, String modelString, int capacityInt, String vehicleId, double basePriceDouble) {
    }

    public Car(String ownerString, String modelString, int capacityInt, int rangeInt, String vehicleId, double basePriceDouble) {
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
