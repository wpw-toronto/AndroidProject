package com.lk.wpw_android_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MapRoute extends AppCompatActivity {

    private Bitmap _mapRouteImage;
    private FirebaseStorage _storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_route);
        _storage = FirebaseStorage.getInstance();

        final ImageView imageView = findViewById(R.id.MapRouteImage);

        StorageReference ref = _storage.getReference().child("WPW_2020_RouteMap.png");

        try {
            final File localFile = File.createTempFile("Images", "bmp");
            ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    _mapRouteImage = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    imageView.setImageBitmap(_mapRouteImage);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MapRoute.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
