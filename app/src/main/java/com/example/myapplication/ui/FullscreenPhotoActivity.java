package com.example.myapplication.ui;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class FullscreenPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_photo);

        ImageView img = findViewById(R.id.imgFull);
        String path = getIntent().getStringExtra("path");
        if (path != null) {
            img.setImageBitmap(BitmapFactory.decodeFile(path));
        }
    }
}
