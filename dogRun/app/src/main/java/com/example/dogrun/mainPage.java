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

public class mainPage extends AppCompatActivity {
    VideoView streamView ;
    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_mode_1);

        streamView = findViewById(R.id.videoView3);
        Uri UriSrc = Uri.parse("http://192.168.1.79:8080/");
        streamView.setVideoURI(UriSrc);
        streamView.requestFocus();
        streamView.start();;


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        streamView.stopPlayback();
    }

}