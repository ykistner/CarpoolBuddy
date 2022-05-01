package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;


//import com.example.carpoolbuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;

public class AddVehicleActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private LinearLayout layout;
    private EditText modelField;
    private EditText range;
    private EditText bicycleType;
    private EditText weight;
    private EditText weightCapacity;
    private EditText basePriceField;
    private EditText capacityField;
    private EditText ownerField;
    private EditText maxAltitude;
    private EditText maxAirspeed;
    private Spinner userRoleSpinner;
    private String selectedRole;
    private String uid;
    private static int uidGenerator = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        layout = findViewById(R.id.linearLayoutAddVehicle);
        userRoleSpinner = findViewById(R.id.spinnerAddVehicle);
        setupSpinner();
        uid = "" + uidGenerator;
        uidGenerator++;
    }

    // setup spinner where user selects what user type they want to make an account for
    private void setupSpinner() {
        String[] userTypes = {"Car", "Segway", "Helicopter", "Bicycle"};
        // add user types to spinner
        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<String>(AddVehicleActivity.this,
                android.R.layout.simple_spinner_item, userTypes);
        langArrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userRoleSpinner.setAdapter(langArrAdapter);

        //triggered whenever user selects something different
        userRoleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedRole = parent.getItemAtPosition(position).toString();
                addFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addFields() {
        commonFields();
        if(selectedRole.equals("Helicopter")) {
            maxAltitude = new EditText(this);
            maxAltitude.setHint("Max Altitude");
            layout.addView(maxAltitude);

            maxAirspeed = new EditText(this);
            maxAirspeed.setHint("Max Airspeed");
            layout.addView(maxAirspeed);
        }
        if(selectedRole.equals("Segway")) {
            range = new EditText(this);
            range.setHint("Range");
            layout.addView(range);

            weightCapacity = new EditText(this);
            weightCapacity.setHint("Weight Capacity");
            layout.addView(weightCapacity);
        }
        if(selectedRole.equals("Car")) {
            range = new EditText(this);
            range.setHint("Range");
            layout.addView(range);
        }
        if(selectedRole.equals("Bicycle")) {
            weightCapacity = new EditText(this);
            weightCapacity.setHint("Weight Capacity");
            layout.addView(weightCapacity);

            bicycleType = new EditText(this);
            bicycleType.setHint("Bicycle Type");
            layout.addView(bicycleType);

            weight = new EditText(this);
            weight.setHint("Weight");
            layout.addView(weight);
        }
    }

    public void commonFields() {
        layout.removeAllViewsInLayout();
        ownerField = new EditText(this);
        ownerField.setHint("Owner");
        layout.addView(ownerField);
        modelField = new EditText(this);
        modelField.setHint("Model");
        layout.addView(modelField);
        capacityField = new EditText(this);
        capacityField.setHint("Capacity");
        layout.addView(capacityField);
        basePriceField = new EditText(this);
        basePriceField.setHint("Base Price");
        layout.addView(basePriceField);
    }


    public void addVehicle(View v) {

        //generate + get new key
        DocumentReference newRideRef = firestore.collection(Constants.VEHICLE_COLLECTION).document();
        String vehicleId = newRideRef.getId();

        //make new vehicle according to selected vehicle type
        Vehicle newVehicle = null;

        //get data from form
        String ownerString = ownerField.getText().toString();
        String modelString = modelField.getText().toString();
        double basePriceDouble = Double.parseDouble(basePriceField.getText().toString());

        if(selectedRole.equals(Constants.HELICOPTER)) {
            int maxAltitudeInt = Integer.parseInt(maxAltitude.getText().toString());
            int maxAirSpeedInt = Integer.parseInt(maxAirspeed.getText().toString());
            newVehicle = new Car(ownerString, modelString, maxAirSpeedInt, maxAltitudeInt, vehicleId, basePriceDouble);
        }
        else if(selectedRole.equals(Constants.CAR)) {
            int capacityInt = Integer.parseInt(capacityField.getText().toString());
            newVehicle = new Car(ownerString, modelString, capacityInt, vehicleId, basePriceDouble);
        }
        else if(selectedRole.equals(Constants.SEGWAY)) {
            int capacityInt = Integer.parseInt(capacityField.getText().toString());
            int rangeInt = Integer.parseInt(range.getText().toString());
            newVehicle = new Segway(ownerString, modelString, capacityInt, rangeInt, vehicleId, basePriceDouble);
        }
        else if(selectedRole.equals(Constants.BICYCLE)) {
            int weight = Integer.parseInt(range.getText().toString());
            String bicycleTypeString = new String(bicycleType.getText().toString());
            double weightCapacityDouble = Integer.parseInt(weightCapacity.getText().toString());
            int rangeInt = Integer.parseInt(range.getText().toString());
            newVehicle = new Car(ownerString, modelString, weight, weightCapacityDouble, bicycleTypeString, rangeInt, vehicleId, basePriceDouble);
        }

        //add the new vehicle to the database
        newRideRef.set(newVehicle);
    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(this,UserProfileActivity.class);
            startActivity(intent);
        }
    }
}