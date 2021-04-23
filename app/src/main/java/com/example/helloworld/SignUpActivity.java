package com.example.helloworld;

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

        textView = findViewById(R.id.welcome_text);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b.containsKey("Username")) {
            String username = b.getString("Username");
            msg = "Thanks for Signing Up " + username + "!";
        }

        textView.setText(msg);
    }

    public void backButtonHandler(View view) {
        finish();
    }
}