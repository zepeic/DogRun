package com.example.dogrun;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.net.Uri;
import android.widget.VideoView;


import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private EditText pin;
    private int correct_pin;
    private Button phone_mode;
    private Button update_pin;
    private Button update_image;
    private static final int PICK_IMAGE = 1;
    VideoView streamView;
    MediaController mediaController;
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
            update_pin = (Button)findViewById(R.id.update_pin);
            update_image = (Button)findViewById(R.id.update_image);
            streamView = findViewById(R.id.videoView);
            Uri UriSrc = Uri.parse("http://192.168.1.79:8080/");
            streamView.setVideoURI(UriSrc);
            mediaController = new MediaController(this);
            streamView.start();


            phone_mode.setOnClickListener( new View.OnClickListener(){
                public void onClick (View v){
                    shoot(v);
                }
            });
            update_pin.setOnClickListener( new View.OnClickListener(){
                public void onClick (View v){
                    change_pin(v);
                }
            });
            update_image.setOnClickListener( new View.OnClickListener(){
                public void onClick (View v){
                    change_image(v);
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Incorrect Pin",

                    Toast.LENGTH_LONG).show();
        }
    }

    private void shoot(View v) {

        Toast.makeText(getApplicationContext(), "PewPew!",

                Toast.LENGTH_LONG).show();

    }
    private void change_image(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_IMAGE);
    }



    private void change_pin(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText et = new EditText(this);
                et.setInputType(InputType.TYPE_CLASS_NUMBER);
                        et.setGravity(Gravity.CENTER_HORIZONTAL);
                        builder.setView(et);
                        builder.setNegativeButton("Cancel", (dialog, id) -> {

                        });
                        builder.setPositiveButton("Ok", (dialog, id) -> {

                        });
        builder.setTitle("Enter New Pin");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                ImageView user_photo = findViewById(R.id.user_photo);
                user_photo.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();


        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        streamView.stopPlayback();
    }



}