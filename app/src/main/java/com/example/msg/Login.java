package com.example.msg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextInputEditText phoneEditText, passwordEditText;
    String phone, password;
    DatabaseReference reference;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phoneEditText = findViewById(R.id.loginPhoneText);
        passwordEditText = findViewById(R.id.loginPasswordText);
        reference = FirebaseDatabase.getInstance().getReference().child("users");
        getSupportActionBar().setTitle("Login Page");
    }

    public void btn_signup(View view) {
        startActivity(new Intent(getApplicationContext(),Signup.class));
    }

    public void btn_login(View view) {
        phone = phoneEditText.getText().toString();
        password = passwordEditText.getText().toString();
        Query query = reference.orderByChild("phone").equalTo(phone);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.getValue(User.class).phone.equals(phone)){
                        user = ds.getValue(User.class);
                        if(user.password.equals(password)){
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}