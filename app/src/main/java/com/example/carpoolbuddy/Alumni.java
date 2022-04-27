package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Alumni extends User{
    private int graduateYear;

    public Alumni(String uid, String name, String email, int graduateYear) {
        super(uid, name, email);
        this.setPriceMultiplier(2);
        this.setUserType("Alumni");
        ArrayList<String> ownedVehicles = new ArrayList<>();
        this.setOwnedVehicles(ownedVehicles);

        double priceMultiplier = 2;
        String userType = "Alumni";
       
        this.graduateYear = graduateYear;
    }

    public int getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(int graduateYear) {
        this.graduateYear = graduateYear;
    }

    @Override
    public String toString() {
        return "Alumni{" +
                "graduateYear=" + graduateYear +
                '}';
    }
}
