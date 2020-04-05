package com.lk.wpw_android_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Donate extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);


        // get the list of stories titles and contents in string array

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.home_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home) {
                    Intent home = new Intent(Donate.this, MapEvent.class);
                    startActivity(home);
                } else if (id == R.id.mapEvent) {
                    Intent mapEvent = new Intent(Donate.this, MapEvent.class);
                    startActivity(mapEvent);
                } else if (id == R.id.mapRoute) {
                    Intent mapRoute = new Intent(Donate.this, MapRoute.class);
                    startActivity(mapRoute);
                } else if (id == R.id.scheduleEvent) {
                    Intent scheduleEvent = new Intent(Donate.this, ScheduleEvent.class);
                    startActivity(scheduleEvent);
                } else if (id == R.id.schedulePerformance) {
                    Intent schedulePerformance = new Intent(Donate.this, SchedulePerformance.class);
                    startActivity(schedulePerformance);
                } else if (id == R.id.foodTruck) {
                    Intent foodTruck = new Intent(Donate.this, FoodTruck.class);
                    startActivity(foodTruck);
                } else if (id == R.id.aboutUs) {
                    Intent aboutUs = new Intent(Donate.this, AboutUs.class);
                    startActivity(aboutUs);
                } else if (id == R.id.signOut) {
                    logout();
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

    public void openDonatePage(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://secure3.convio.net/akfc/site/Donation2?idb=1849506191&df_id=1831&FR_ID=1617&PROXY_ID=1015557&PROXY_TYPE=20&1831.donation=form1"));
        startActivity(browserIntent);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent signOut = new Intent(Donate.this, MainDisplay.class);
        signOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(signOut);
    }
}
