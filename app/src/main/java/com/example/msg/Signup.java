package com.example.msg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Signup extends AppCompatActivity {

    TextInputEditText nameTextInputEditText, phoneTextInputEditText, passwordTextInputEditText;
    RadioGroup genderRadioGroup;
    RadioButton genderRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        getSupportActionBar().setTitle("Signup Page");
        nameTextInputEditText = findViewById(R.id.nameTextInput);
        phoneTextInputEditText = findViewById(R.id.phoneTextInput);
        passwordTextInputEditText = findViewById(R.id.passwordTextInput);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        Button signupButton = findViewById(R.id.registerButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int buttonId = genderRadioGroup.getCheckedRadioButtonId();
                genderRadioButton = findViewById(buttonId);
                User newUser = new User();
                newUser.name = nameTextInputEditText.getText().toString();
                newUser.phone = phoneTextInputEditText.getText().toString();
                newUser.password = passwordTextInputEditText.getText().toString();
                newUser.gender = genderRadioButton.getText().toString();
                Random random = new Random();
                reference.child(String.valueOf(random.nextInt(1000))).setValue(newUser);
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}