package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Student extends User{
    private String graduatingYear;
    private ArrayList<String> parentUIDs;

    public Student(String uid, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, String graduatingYear, ArrayList<String> parentUIDs) {
        super(uid, name, email, userType, priceMultiplier, ownedVehicles);
        this.graduatingYear = graduatingYear;
        this.parentUIDs = parentUIDs;
    }

    public String getGraduatingYear() {
        return graduatingYear;
    }

    public void setGraduatingYear(String graduatingYear) {
        this.graduatingYear = graduatingYear;
    }

    public ArrayList<String> getParentUIDs() {
        return parentUIDs;
    }

    public void setParentUIDs(ArrayList<String> parentUIDs) {
        this.parentUIDs = parentUIDs;
    }

    @Override
    public String toString() {
        return "Student{" +
                "graduatingYear='" + graduatingYear + '\'' +
                ", parentUIDs=" + parentUIDs +
                '}';
    }
}
