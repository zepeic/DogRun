package com.example.dogrun;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class setupPage extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private EditText pin;
    private EditText pin_confirmation;
    public boolean setupFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_screen);


    }

    public void Complete(View v) {
        System.out.println("This is the starting point");
        EditText pin = findViewById(R.id.pin);
        EditText pin_confirmation = findViewById(R.id.pin_confirmation);

        String pin_input = pin.getText().toString();
        String pin_input_confirmation = pin_confirmation.getText().toString();
        if (pin_input.matches("")) {
            Toast.makeText(this, "You did not enter a pin", Toast.LENGTH_SHORT).show();
            return;
        }


        //System.out.println("We actually get here " + pin_input_confirmation + " " + pin_input);
        int int_pin = parseInt(pin_input);
        int int_pin_confirm = parseInt(pin_input_confirmation);

        if (int_pin == int_pin_confirm) {
            SharedPreferences stored_pin  = getSharedPreferences("stored_pin",MODE_PRIVATE);
            Toast.makeText(getApplicationContext(), "Success!",

                    Toast.LENGTH_LONG).show();

            Intent i = new Intent(getBaseContext(), MainActivity.class);
            i.putExtra("key", int_pin);
            //(R.layout.activity_main);
            SharedPreferences.Editor pin_editor = stored_pin.edit();

            pin_editor.putInt("pin",int_pin);
            pin_editor.apply();
            startActivity(i);
            finish();

        } else if (int_pin != int_pin_confirm) {
            Toast.makeText(getApplicationContext(), "Pins do not match",

                    Toast.LENGTH_LONG).show();
        }
    }

    public void Upload(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_IMAGE);


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

}

