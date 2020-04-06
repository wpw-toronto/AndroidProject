package com.lk.wpw_android_project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AboutUs extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // get the list of stories titles and contents in string array
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navi_about_us);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                final int id = menuItem.getItemId();
                if (id == R.id.home){
                    Intent home = new Intent(AboutUs.this, Home.class);
                    startActivity(home);
                } else if(id == R.id.mapEvent){
                    Intent mapEvent = new Intent(AboutUs.this, MapEvent.class);
                    startActivity(mapEvent);
                } else if(id == R.id.mapRoute){
                    Intent mapRoute = new Intent(AboutUs.this, MapRoute.class);
                    startActivity(mapRoute);
                } else if(id == R.id.scheduleEvent){
                    Intent schedulePerformance = new Intent(AboutUs.this, ScheduleEvent.class);
                    startActivity(schedulePerformance);
                } else if(id == R.id.schedulePerformance){
                    Intent schedulePerformance = new Intent(AboutUs.this, SchedulePerformance.class);
                    startActivity(schedulePerformance);
                } else if(id == R.id.foodTruck){
                    Intent foodTruck = new Intent(AboutUs.this, FoodTruck.class);
                    startActivity(foodTruck);
                } else if(id == R.id.donate){
                    Intent donate = new Intent(AboutUs.this, Donate.class);
                    startActivity(donate);
                } else if(id == R.id.aboutUs){
                    drawer.closeDrawers();
                } else if(id == R.id.signOut){
                    logout();
                }
                return true;
            }
        });

        navigationView.setActivated(false);

        drawer = findViewById(R.id.drawer_about_us);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
    }

    // Hide Navigation Drawer on Pause
    @Override
    protected void onPause() {
        drawer.closeDrawers();
        super.onPause();
    }


    private void logout() {
        FirebaseAuth.getInstance().signOut();

        Intent signOut = new Intent(AboutUs.this, MainDisplay.class);
        signOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(signOut);
    }

    // go to facebook
    public void goToFacebook (View view) {
        String url = "https://www.facebook.com/akfcanada/";
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    // go to twitter
    public void goToTwitter (View view) {
        String url = "https://twitter.com/AKFCanada";
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    //go to youtube
    public void goToYoutube (View view) {
        String url = "https://www.youtube.com/user/akfcadmin";
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    //send mail
    public void sendMail (View view) {
        String mailto = "mailto:heons921@gmail.com";

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            //TODO: Handle case where no email app is available
        }
    }
}
