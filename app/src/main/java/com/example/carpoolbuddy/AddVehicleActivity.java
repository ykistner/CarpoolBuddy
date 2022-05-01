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
        String ownerString = ownerField.getText().toString();
        String modelString = modelField.getText().toString();
        String capacityString = capacityField.getText().toString();
        String basePriceString = basePriceField.getText().toString();

        if(selectedRole.equals("Helicopter")) {
            int maxAltitudeInt = Integer.parseInt(maxAltitude.getText().toString());
            int maxAirSpeedInt = Integer.parseInt(maxAirspeed.getText().toString());
            Helicopter newVehicle = new Helicopter(uid, ownerString, capacityString, modelString, basePriceString, maxAltitudeInt, maxAirSpeedInt);
            uidGenerator++;
            firestore.collection("vehicle").document(uid).set(newVehicle);
        }
        if(selectedRole.equals("Car")) {
            int rangeInt = Integer.parseInt(range.getText().toString());
            Car newVehicle = new Car(uid, ownerString, capacityString, modelString, basePriceString, rangeInt);
            uidGenerator++;
            firestore.collection("vehicle").document(uid).set(newVehicle);
        }
        if(selectedRole.equals("Segway")) {
            int rangeInt = Integer.parseInt(range.getText().toString());
            double weightCapacityDouble = Integer.parseInt(weightCapacity.getText().toString());
            Car newVehicle = new Car(uid, ownerString, capacityString, modelString, basePriceString, rangeInt, weightCapacityDouble);
            uidGenerator++;
            firestore.collection("vehicle").document(uid).set(newVehicle);
        }
        if(selectedRole.equals("Bicycle")) {
            int weight = Integer.parseInt(range.getText().toString());
            String bicycleTypeString = new String(bicycleType.getText().toString());
            double weightCapacityDouble = Integer.parseInt(weightCapacity.getText().toString());
            Car newVehicle = new Car(uid, ownerString, capacityString, modelString, basePriceString, weight, bicycleTypeString, weightCapacityDouble);
            uidGenerator++;
            firestore.collection("vehicle").document(uid).set(newVehicle);
        }
    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(this,UserProfileActivity.class);
            startActivity(intent);
        }
    }
}