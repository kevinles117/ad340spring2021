package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    DatePickerDialog datePicker;
    EditText birthDateText;
    EditText nameEditText;
    EditText emailEditText;
    EditText usernameEditText;
    String name;
    String email;
    String username;
    int age;
    Button getButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        birthDateText = (EditText) findViewById(R.id.birth_date);
        birthDateText.setInputType(InputType.TYPE_NULL);
        birthDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePicker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                birthDateText.setText((monthOfYear +1) + "/" + dayOfMonth + "/" + year);
                                age = getAge(year, monthOfYear, dayOfMonth);
                                Log.i(TAG,"Age: " + String.valueOf(age));
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        nameEditText.setText("");
//        emailEditText.setText("");
//        usernameEditText.setText("");
    }

    public void signUpButtonHandler(View view) {

        nameEditText = (EditText) findViewById(R.id.name);
        name = nameEditText.getText().toString();
        Log.i(TAG, "Name: " + name);

        emailEditText = (EditText) findViewById(R.id.email);
        email = emailEditText.getText().toString();
        Log.i(TAG,"Email: " + email);

        usernameEditText = (EditText) findViewById(R.id.username);
        username = usernameEditText.getText().toString();
        Log.i(TAG ,"Username: " + username);

        if(
                (name != null && name.trim().isEmpty()) ||
                (email != null && email.trim().isEmpty()) ||
                (username != null && username.trim().isEmpty()) ) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill out all forms", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        Intent intent = new Intent(this, SignUpActivity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public int getAge(int year, int month, int dayOfMonth) {
        return Period.between(LocalDate.of(year, month, dayOfMonth), LocalDate.now()).getYears();
    }

}