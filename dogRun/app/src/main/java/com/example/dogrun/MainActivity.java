package com.example.dogrun;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private int correct_pin = 1234;
    private EditText pin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_screen);
        pin = findViewById(R.id.pin);
    }

    public void submit(View v) {
        String pin_input = pin.getText().toString();
        int int_pin = parseInt(pin_input);
        if (int_pin == correct_pin) {
            Toast.makeText(getApplicationContext(), "Success!",

                    Toast.LENGTH_LONG).show();
            setContentView(R.layout.option1);
        }
        else{
            Toast.makeText(getApplicationContext(), "Incorrect Pin",

                    Toast.LENGTH_LONG).show();
        }
    }


    public void phone_mode_click(View v) {

        Toast.makeText(getApplicationContext(), "Phone Mode",

                Toast.LENGTH_LONG).show();
                setContentView(R.layout.phone_mode);

    }
    public void complete_click(View v) {

        Toast.makeText(getApplicationContext(), "Setup Complete",

                Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);

    }
}

