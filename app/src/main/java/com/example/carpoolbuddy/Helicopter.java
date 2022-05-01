package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Helicopter extends Vehicle{
    private int maxAltitude;
    private int maxAirSpeed;

    public Helicopter(String owner, String model, String vehicleID, String vehicleType, double basePrice, int maxAltitude, int maxAirSpeed) {
        super(owner, model, vehicleID, vehicleType, basePrice);
        this.maxAltitude = maxAltitude;
        this.maxAirSpeed = maxAirSpeed;
    }

    public Helicopter(String uid, String ownerString, String capacityString, String modelString, String basePriceString, int maxAltitudeInt, int maxAirSpeedInt) {
    }

    public int getMaxAltitude() {
        return maxAltitude;
    }

    public void setMaxAltitude(int maxAltitude) {
        this.maxAltitude = maxAltitude;
    }

    public int getMaxAirSpeed() {
        return maxAirSpeed;
    }

    public void setMaxAirSpeed(int maxAirSpeed) {
        this.maxAirSpeed = maxAirSpeed;
    }

    @Override
    public String toString() {
        return "Helicopter{" +
                "maxAltitude=" + maxAltitude +
                ", maxAirSpeed=" + maxAirSpeed +
                '}';
    }
}
