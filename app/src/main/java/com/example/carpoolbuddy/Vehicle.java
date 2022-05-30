package com.example.carpoolbuddy;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Vehicle implements Serializable, Parcelable {
    private String owner;
    private String model;
    private int capacity;
    private int remainingCapacity;
    private String vehicleID;
    private ArrayList<String> ridersUIDs;


    private ArrayList<String> reservedUIDs;
    private boolean open;
    private String vehicleType;
    private double basePrice;

    public Vehicle() {

    }

    public Vehicle(String ownerString, String modelString, int capacityInt, String vehicleId, double basePriceDouble) {
        this.owner = ownerString;
        this.model = modelString;
        this.capacity = capacityInt;
        remainingCapacity = capacity;
        this.vehicleID = vehicleId;
        this.basePrice = basePriceDouble;
        ridersUIDs = new ArrayList<>();
        reservedUIDs = new ArrayList<>();
        this.vehicleType = "";
        this.open = true;
    }
    public Vehicle(String owner, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice) {
        this.owner = owner;
        this.model = model;
        this.capacity = capacity;
        remainingCapacity = capacity;
        this.vehicleID = vehicleID;
        this.ridersUIDs = ridersUIDs;
        this.open = open;
        this.vehicleType = vehicleType;
        this.basePrice = basePrice;
    }

    public Vehicle(String owner, String model, double basePrice, int capacity) {
    }

    public Vehicle(String owner, String model, String vehicleID, String vehicleType, double basePrice) {
    }

    protected Vehicle(Parcel in) {
        owner = in.readString();
        model = in.readString();
        capacity = in.readInt();
        remainingCapacity = in.readInt();
        vehicleID = in.readString();
        ridersUIDs = in.createStringArrayList();
        open = in.readByte() != 0;
        vehicleType = in.readString();
        basePrice = in.readDouble();
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public ArrayList<String> getRidersUIDs() {
        return ridersUIDs;
    }

    public void setRidersUIDs(ArrayList<String> ridersUIDs) {
        this.ridersUIDs = ridersUIDs;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public void setRemainingCapacity(int remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }

    public ArrayList<String> getReservedUIDs() {
        return reservedUIDs;
    }

    public void setReservedUIDs(ArrayList<String> reservedUIDs) {
        this.reservedUIDs = reservedUIDs;
    }

    public void addReservedUid(String id){
        reservedUIDs.add(id);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "owner='" + owner + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", vehicleID='" + vehicleID + '\'' +
                ", ridersUIDs=" + ridersUIDs +
                ", open=" + open +
                ", vehicleType='" + vehicleType + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(owner);
        dest.writeString(model);
        dest.writeInt(capacity);
        dest.writeInt(remainingCapacity);
        dest.writeString(vehicleID);
        dest.writeStringList(ridersUIDs);
        dest.writeByte((byte) (open ? 1 : 0));
        dest.writeString(vehicleType);
        dest.writeDouble(basePrice);
    }
}
