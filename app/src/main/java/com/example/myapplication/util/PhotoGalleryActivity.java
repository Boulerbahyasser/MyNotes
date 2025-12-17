package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapter.PhotoAdapter;
import com.example.myapplication.util.PhotoStorage;

import java.util.ArrayList;

public class PhotoGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);

        GridView grid = findViewById(R.id.gridPhotos);
        TextView empty = findViewById(R.id.tvEmpty);

        ArrayList<String> paths = PhotoStorage.getPhotoPaths(this);

        if (paths.isEmpty()) {
            empty.setVisibility(View.VISIBLE);
        } else {
            empty.setVisibility(View.GONE);
        }

        PhotoAdapter adapter = new PhotoAdapter(this, paths);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(this, FullscreenPhotoActivity.class);
            i.putExtra("path", paths.get(position));
            startActivity(i);
        });
    }
}
