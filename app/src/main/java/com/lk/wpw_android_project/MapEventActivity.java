package com.lk.wpw_android_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MapEventActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_event);

        // get the list of stories titles and contents in string array

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.map_event_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.home){
                    Intent home = new Intent(MapEventActivity.this, MainActivity.class);
                    startActivity(home);
                }else if(id == R.id.mapEvent){
                    Intent mapEvent = new Intent(MapEventActivity.this, MainActivity.class);
                    startActivity(mapEvent);
                }else if(id == R.id.mapRoute){
                    Intent mapRoute = new Intent(MapEventActivity.this, MainActivity.class);
                    startActivity(mapRoute);
                }
                else if(id == R.id.scheduleEvent){
                    Intent scheduleEvent = new Intent(MapEventActivity.this, MainActivity.class);
                    startActivity(scheduleEvent);
                }
                else if(id == R.id.schedulePerformace){
                    Intent schedulePerformace = new Intent(MapEventActivity.this, MainActivity.class);
                    startActivity(schedulePerformace);
                }
                else if(id == R.id.foodTruck){
                    Intent foodTruck = new Intent(MapEventActivity.this, MainActivity.class);
                    startActivity(foodTruck);
                }
                else if(id == R.id.aboutUs){
                    Intent aboutUs = new Intent(MapEventActivity.this, MainActivity.class);
                    startActivity(aboutUs);
                }
                else if(id == R.id.signOut){
                    //logout();
                }
                return true;
            }
        });

        drawer = findViewById(R.id.drawerMapEvent);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
    }



    private void logout() {
//        FirebaseAuth.getInstance().signOut();
//
//        Intent signOut = new Intent(MapEventActivity.this, MainActivity.class);
//        startActivity(signOut);

    }
}
