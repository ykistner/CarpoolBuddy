package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText emailField;
    private EditText passwordField;

    @Override
    public void onStart(){
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        emailField = findViewById(R.id.editTextEmail);
        passwordField = findViewById(R.id.editTextPassword);
    }

    public void signIn(View v) {
        System.out.println("Login");
        String emailString = emailField.getText().toString();
        String passwordString = passwordField.getText().toString();
        System.out.println(String.format("Email: %s and Password %s", emailString, passwordString));

        mAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Login: ", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Login: ", "signInWithEmail:failure", task.getException());
                    updateUI(null);
                }
            }
        });


        ArrayList<String> myCars = new ArrayList<>();
        myCars.add("Tesla"); myCars.add("Toyota"); myCars.add("BMW");
        myCars.add("Audi"); myCars.add("Mercedes"); myCars.add("VW");

        ArrayList<String> myRidersUID = new ArrayList<>();
        myRidersUID.add(UUID.randomUUID().toString());

        User thisUser = new User(UUID.randomUUID().toString(), "Yuuto Kistner", "Yuuto_Kistner@fis.edu", "Student", 2, myCars);
        Vehicle thisVehicle = new Vehicle("Tom Mayock", "Audi Q5", 4, UUID.randomUUID().toString(), myRidersUID, true, "SUV", 7.99);

        User thatUser = new User(UUID.randomUUID().toString(), "Rikuto Kimura", "Rikuto_Kimura@fis.edu", "Student", 4, myCars);
        Vehicle thatVehicle = new Vehicle("Kagan Angin", "Ford Fiesta", 3, UUID.randomUUID().toString(), myRidersUID, true, "Hatchback", 5.99);


        firestore.collection("everyones-items").document("kistner").collection("User").document(thisUser.getUid()).set(thisUser);
        firestore.collection("everyones-items").document("kistner").collection("Vehicle").document(thisVehicle.getVehicleID()).set(thisVehicle);

        firestore.collection("everyones-items/kistner/User").document(thatUser.getUid()).set(thatUser);
        firestore.collection("everyones-items/kistner/Vehicle").document(thatVehicle.getVehicleID()).set(thatVehicle);

        firestore.collection("everyones-items").document("chandler").collection("books").document(thisUser.getUid()).set(thisUser);

        Log.d("KISTNER_TEST", "ID to look for: " + thatUser.getUid());

        firestore.collection("everyones-items/kistner/User").document(thatUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot ds = task.getResult();

                    Vehicle myVehicle = ds.toObject(Vehicle.class);
                    User myUser = ds.toObject(User.class);

//                    Log.d("KISTNER_TEST_VEHICLEMODEL", myVehicle.getModel());
//                    Log.d("KISTNER_TEXT_USERNAME: ", myUser.getName());

                }
                else{

                    }
                }
            });
        }

    public void signUp(View v) {
        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivity(intent);
//        System.out.println("Sign Up");
//        String emailString = emailField.getText().toString();
//        String passwordString = passwordField.getText().toString();
//
//        mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    Log.d("SIGN UP", "Successfully signed up the user");
//
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    updateUI(user);
//                } else {
//                    Log.w("Sign up", "createUserWithEmail:failure", task.getException());
//                    updateUI(null);
//                }
//            }
//        });
//
    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }
    }


}