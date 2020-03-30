package com.lk.wpw_android_project;

import androidx.annotation.NonNull;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.lk.wpw_android_project.Model.ScheduleEventList;

import java.text.ParseException;
import java.util.Date;

public class ScheduleEvent extends AppCompatActivity {

    ListView listView;
    FirebaseListAdapter adapter;
    ScheduleEventList scheduleEventList;
    TextView categories;

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_event);

        // get the list of stories titles and contents in string array
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navi_schedule_event);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.home){
                    Intent home = new Intent(ScheduleEvent.this, Home.class);
                    startActivity(home);
                }else if(id == R.id.mapEvent){
                    Intent mapEvent = new Intent(ScheduleEvent.this, MapEvent.class);
                    startActivity(mapEvent);
                }else if(id == R.id.mapRoute){
                    Intent mapRoute = new Intent(ScheduleEvent.this, MapRoute.class);
                    startActivity(mapRoute);
                }
                else if(id == R.id.scheduleEvent){
                    Intent scheduleEvent = new Intent(ScheduleEvent.this, ScheduleEvent.class);
                    startActivity(scheduleEvent);
                }
                else if(id == R.id.schedulePerformance){
                    Intent schedulePerformance = new Intent(ScheduleEvent.this, SchedulePerformance.class);
                    startActivity(schedulePerformance);
                }
                else if(id == R.id.foodTruck){
                    Intent foodTruck = new Intent(ScheduleEvent.this, FoodTruck.class);
                    startActivity(foodTruck);
                }
                else if(id == R.id.aboutUs){
                    Intent aboutUs = new Intent(ScheduleEvent.this, AboutUs.class);
                    startActivity(aboutUs);
                }
                else if(id == R.id.signOut){
                   logout();
                }
                return true;
            }
        });


        drawer = findViewById(R.id.drawer_schedule_event);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        scheduleEventList = new ScheduleEventList();

        listView = (ListView)findViewById(R.id.listView);

        Query query = FirebaseDatabase.getInstance().getReference().child("ScheduleEvent");

        FirebaseListOptions<ScheduleEventList> options = new FirebaseListOptions.Builder<ScheduleEventList>()
                .setLayout(R.layout.activity_schedule_layout)
                .setQuery(query, ScheduleEventList.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, final int position) {
                TextView time = v.findViewById(R.id.scheduleTime);
                TextView description= v.findViewById(R.id.scheduleDescription);
                RelativeLayout relativeLayout = v.findViewById(R.id.scheduleListTime);

                final ScheduleEventList scheduleEventList = (ScheduleEventList) model;

                // get the current date
                Date date = new Date() ;
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a") ;
                dateFormat.format(date);
                //System.out.println(dateFormat.format(date));

                try {
                    // if the time is greater than given time
                    if(dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse(scheduleEventList.getTime())))
                    {
                        relativeLayout.setBackgroundColor(Color.parseColor("#6532A8"));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                time.setText(scheduleEventList.getTime());
                description.setText(scheduleEventList.getDescription());
            }
        };

        listView.setAdapter(adapter);
    }


    private void logout() {
        FirebaseAuth.getInstance().signOut();

        Intent signOut = new Intent(ScheduleEvent.this, Home.class);
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


