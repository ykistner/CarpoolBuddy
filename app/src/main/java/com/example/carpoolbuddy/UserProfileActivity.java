package com.example.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
}