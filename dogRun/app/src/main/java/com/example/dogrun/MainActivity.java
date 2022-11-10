package com.example.dogrun;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {
    private EditText pin;
    private int correct_pin;
    private Button phone_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences stored_pin = getSharedPreferences("stored_pin", MODE_PRIVATE);
        boolean firstState = prefs.getBoolean("firstStart", true);



        if (firstState) {
            Intent intent = new Intent(MainActivity.this, setupPage.class);
            MainActivity.this.startActivity(intent);
            intent = getIntent();
            int pin_val = intent.getIntExtra("key", 0);
            System.out.println("\nPin in Mainpage : pin_val");
            correct_pin = stored_pin.getInt("stored_pin", 0);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putBoolean("firstStart", false);
            editor.apply();
            finish();

        }

    }


    public void submit(View v) {
        pin = findViewById(R.id.pin);
        String pin_input = pin.getText().toString();
        int int_pin = parseInt(pin_input);
        if (int_pin == correct_pin) {
            Toast.makeText(getApplicationContext(), "Success!",

                    Toast.LENGTH_LONG).show();
            setContentView(R.layout.option1);
            phone_mode = (Button)findViewById(R.id.phoneMode);
            phone_mode.setOnClickListener( new View.OnClickListener(){
                public void onClick (View v){
                    next_page(v);
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Incorrect Pin",

                    Toast.LENGTH_LONG).show();
        }
    }

    private void next_page(View v) {
        Toast.makeText(getApplicationContext(), "Success!",

                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, mainPage.class);
        MainActivity.this.startActivity(intent);
    }


}


