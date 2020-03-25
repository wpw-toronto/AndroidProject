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

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the list of stories titles and contents in string array

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.home_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.home){
                    Intent home = new Intent(MainActivity.this, MapEventActivity.class);
                    startActivity(home);
                }else if(id == R.id.mapEvent){
                    Intent mapEvent = new Intent(MainActivity.this, MapEventActivity.class);
                    startActivity(mapEvent);
                }else if(id == R.id.mapRoute){
                    Intent mapRoute = new Intent(MainActivity.this, MapEventActivity.class);
                    startActivity(mapRoute);
                }
                else if(id == R.id.scheduleEvent){
                    Intent scheduleEvent = new Intent(MainActivity.this, MapEventActivity.class);
                    startActivity(scheduleEvent);
                }
                else if(id == R.id.schedulePerformace){
                    Intent schedulePerformace = new Intent(MainActivity.this, MapEventActivity.class);
                    startActivity(schedulePerformace);
                }
                else if(id == R.id.foodTruck){
                    Intent foodTruck = new Intent(MainActivity.this, MapEventActivity.class);
                    startActivity(foodTruck);
                }
                else if(id == R.id.aboutUs){
                    Intent aboutUs = new Intent(MainActivity.this, MapEventActivity.class);
                    startActivity(aboutUs);
                }
                else if(id == R.id.signOut){
                    //logout();
                }
                return true;
            }
        });

        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
    }
}
