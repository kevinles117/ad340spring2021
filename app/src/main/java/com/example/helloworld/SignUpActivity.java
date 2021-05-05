package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    String name;
    TextView nameTextView;
    String occupation;
    TextView occupationTextView;
    int age;
    String desc;
    TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Sets up the TextViews
        nameTextView = findViewById(R.id.name_text);
        occupationTextView = findViewById(R.id.occupation_text);
        descriptionTextView = findViewById(R.id.description_text);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b.containsKey("Name") && b.containsKey("Description") && b.containsKey("Occupation") && b.containsKey("Age")) {
            name = b.getString("Name");
            occupation = b.getString("Occupation");
            age = b.getInt("Age");
            desc = b.getString("Description");
        }
        nameTextView.setText(name + ", " + age);
        occupationTextView.setText(occupation);
        descriptionTextView.setText(desc);

        ProfileFragment profileFragment = new ProfileFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.linearLayout, profileFragment, "profileFragment");
        transaction.commit();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Sets the message in case of screen rotation
        if (savedInstanceState.containsKey("Name")) {
            nameTextView.setText((String)savedInstanceState.get("Name"));
        }

        if (savedInstanceState.containsKey("Occupation")) {
            occupationTextView.setText((String)savedInstanceState.get("Occupation"));
        }

        if (savedInstanceState.containsKey("Description")) {
            descriptionTextView.setText((String)savedInstanceState.get("Description"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Saves the message in case of screen rotation
        outState.putString("Name", nameTextView.getText().toString());
        outState.putString("Occupation", occupationTextView.getText().toString());
        outState.putString("Description", descriptionTextView.getText().toString());
    }
}