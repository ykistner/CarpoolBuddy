package com.example.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.rpc.Help;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //add comment
    }

    public void goToVehicles(View v) {
        Intent nextScreen = new Intent(getBaseContext(), VehiclesInfoActivity.class);
        startActivity(nextScreen);
    }

    public void addVehicles(View v) {
        Intent nextScreen = new Intent(getBaseContext(), AddVehicleActivity.class);
        startActivity(nextScreen);
    }

    public void goToSignOut(View v) {
        Intent nextScreen = new Intent(getBaseContext(), AuthActivity.class);
        startActivity(nextScreen);
    }

    public void goToHelp(View v){
        Intent nextScreen = new Intent(getBaseContext(), HelpActivity.class);
        startActivity(nextScreen);
    }
}