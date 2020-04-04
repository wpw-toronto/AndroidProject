package com.lk.wpw_android_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MapRoute extends AppCompatActivity {

    private Bitmap _mapRouteImage;
    private FirebaseStorage _storage;

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_route);

        // get the list of stories titles and contents in string array
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navi_map_route_event);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.home){
                    Intent home = new Intent(MapRoute.this, Home.class);
                    startActivity(home);
                }else if(id == R.id.mapEvent){
                    Intent mapEvent = new Intent(MapRoute.this, MapEvent.class);
                    startActivity(mapEvent);
                }else if(id == R.id.mapRoute){
                    Intent mapRoute = new Intent(MapRoute.this, MapRoute.class);
                    startActivity(mapRoute);
                }
                else if(id == R.id.scheduleEvent){
                    Intent scheduleEvent = new Intent(MapRoute.this, ScheduleEvent.class);
                    startActivity(scheduleEvent);
                }
                else if(id == R.id.schedulePerformance){
                    Intent schedulePerformance = new Intent(MapRoute.this, SchedulePerformance.class);
                    startActivity(schedulePerformance);
                }
                else if(id == R.id.foodTruck){
                    Intent foodTruck = new Intent(MapRoute.this, FoodTruck.class);
                    startActivity(foodTruck);
                }
                else if(id == R.id.aboutUs){
                    Intent aboutUs = new Intent(MapRoute.this, AboutUs.class);
                    startActivity(aboutUs);
                }
                else if(id == R.id.signOut){
                    logout();
                }
                return true;
            }
        });

        drawer = findViewById(R.id.drawer_map_route_event);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();



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

    private void logout() {
        FirebaseAuth.getInstance().signOut();

        Intent signOut = new Intent(MapRoute.this, Home.class);
        startActivity(signOut);

    }
}
