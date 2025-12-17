package com.example.myapplication.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.myapplication.R;
import com.example.myapplication.util.PhotoStorage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PhotoCaptureActivity extends AppCompatActivity {

    private ImageView preview;
    private String currentPhotoPath;

    private final ActivityResultLauncher<String> requestCameraPermission =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted -> {
                if (granted) launchCamera();
                else Toast.makeText(this, "Permission caméra refusée.", Toast.LENGTH_SHORT).show();
            });

    private final ActivityResultLauncher<Uri> takePictureLauncher =
            registerForActivityResult(new ActivityResultContracts.TakePicture(), success -> {
                if (success && currentPhotoPath != null) {
                    PhotoStorage.addPhotoPath(this, currentPhotoPath);
                    preview.setImageURI(Uri.fromFile(new File(currentPhotoPath)));
                    Toast.makeText(this, "Photo enregistrée.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Capture annulée.", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_capture);

        preview = findViewById(R.id.imgPreview);
        Button btnTake = findViewById(R.id.btnTakePhoto);
        Button btnGallery = findViewById(R.id.btnOpenGallery);

        btnTake.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                launchCamera();
            } else {
                requestCameraPermission.launch(Manifest.permission.CAMERA);
            }
        });

        btnGallery.setOnClickListener(v -> {
            startActivity(new Intent(this, PhotoGalleryActivity.class));
        });
    }

    private void launchCamera() {
        try {
            File photoFile = createImageFile();
            Uri uri = FileProvider.getUriForFile(
                    this,
                    getPackageName() + ".fileprovider",
                    photoFile
            );
            takePictureLauncher.launch(uri);
        } catch (Exception e) {
            Toast.makeText(this, "Erreur caméra: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private File createImageFile() throws Exception {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (storageDir == null) throw new Exception("Storage indisponible");

        File image = new File(storageDir, "IMG_" + timeStamp + ".jpg");
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
