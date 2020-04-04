package com.lk.wpw_android_project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.lk.wpw_android_project.Model.SchedulePerformanceList;

import java.text.ParseException;
import java.util.Date;

public class SchedulePerformance extends AppCompatActivity {

    ListView listView;
    FirebaseListAdapter adapter;

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_performance);

        // get the list of stories titles and contents in string array
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navi_schedule_performance);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                final int id = menuItem.getItemId();
                if (id == R.id.home){
                    Intent home = new Intent(SchedulePerformance.this, Home.class);
                    startActivity(home);
                } else if(id == R.id.mapEvent){
                    Intent mapEvent = new Intent(SchedulePerformance.this, MapEvent.class);
                    startActivity(mapEvent);
                } else if(id == R.id.mapRoute){
                    Intent mapRoute = new Intent(SchedulePerformance.this, MapRoute.class);
                    startActivity(mapRoute);
                } else if(id == R.id.scheduleEvent){
                    Intent scheduleEvent = new Intent(SchedulePerformance.this, ScheduleEvent.class);
                    startActivity(scheduleEvent);
                } else if(id == R.id.schedulePerformance){
                    drawer.closeDrawers();
                } else if(id == R.id.foodTruck){
                    Intent foodTruck = new Intent(SchedulePerformance.this, FoodTruck.class);
                    startActivity(foodTruck);
                } else if(id == R.id.aboutUs){
                    Intent aboutUs = new Intent(SchedulePerformance.this, AboutUs.class);
                    startActivity(aboutUs);
                } else if(id == R.id.signOut){
                    logout();
                }
                return true;
            }
        });

        drawer = findViewById(R.id.drawer_schedule_performance);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        listView = (ListView)findViewById(R.id.listView);

        Query query = FirebaseDatabase.getInstance().getReference().child("SchedulePerformance");

        FirebaseListOptions<SchedulePerformanceList> options = new FirebaseListOptions.Builder<SchedulePerformanceList>()
                .setLayout(R.layout.activity_schedule_layout)
                .setQuery(query,SchedulePerformanceList.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected void populateView(View v, Object model, final int position) {
                TextView time = v.findViewById(R.id.scheduleTime);
                TextView description= v.findViewById(R.id.scheduleDescription);
                RelativeLayout relativeLayout = v.findViewById(R.id.scheduleListTime);

                final SchedulePerformanceList schedulePerformanceList = (SchedulePerformanceList) model;

                // get the current date
                Date date = new Date() ;
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a") ;
                dateFormat.format(date);

                try {
                    // if the time is greater than given time
                    if(dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse(schedulePerformanceList.getStartTime())) &&
                            dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse(schedulePerformanceList.getEndTime()))
                    )
                    {
                        relativeLayout.setBackgroundColor(Color.parseColor("#6532A8"));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                time.setText(schedulePerformanceList.getStartTime() + " - " + schedulePerformanceList.getEndTime());
                description.setText(schedulePerformanceList.getDescription());
            }
        };

        listView.setAdapter(adapter);
    }

    // Hide Navigation Drawer on Pause
    @Override
    protected void onPause() {
        drawer.closeDrawers();
        super.onPause();
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();

        Intent signOut = new Intent(SchedulePerformance.this, MainDisplay.class);
        signOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(signOut);
    }

    public void onStart(){
        super.onStart();
        adapter.startListening();
    }

    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }


}
