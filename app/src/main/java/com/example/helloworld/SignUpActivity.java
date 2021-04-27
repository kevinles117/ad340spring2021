package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    String msg;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Sets up welcome_text TextView
        textView = findViewById(R.id.welcome_text);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b.containsKey("Username")) {
            String username = b.getString("Username");
            msg = getString(R.string.sign_up_message) + username + "!";
        }

        textView.setText(msg);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Sets the message in case of screen rotation
        if (savedInstanceState.containsKey("Username")) {
            textView.setText((String)savedInstanceState.get("message"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Saves the message in case of screen rotation
        outState.putString("message", textView.getText().toString());
    }

    public void backButtonHandler(View view) {
        finish();
    }
}