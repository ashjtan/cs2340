package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import cs2340.group61.doughnation.DatabaseAccess;
import cs2340.group61.doughnation.controller.ViewLocationsActivity;
import cs2340.group61.doughnation.model.Location;

import cs2340.group61.doughnation.R;

public class LocationDetailsActivity extends AppCompatActivity {

    private static final String TAG = "LocationDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);

        //Create button to go back to location list screen
        Button backButton = (Button) findViewById(R.id.back_Locations_View_button);

        //Create button to logout
        Button logout = (Button) findViewById(R.id.return_login_Button);

        //Click listener to go back to previous screen
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationDetailsActivity.this,
                        ViewLocationsActivity.class));
            }
        });

        //Click listener to logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationDetailsActivity.this,
                        LoginActivity.class));
            }
        });

        getIncomingIntent();
    }


    //Get information passed in from RecyclerViewAdapter
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: Checking for incoming intent.");
        if(getIntent().hasExtra("location_name")) {
            Log.d(TAG, "getIncomingIntent: Found intent extras");

            String locationName = getIntent().getStringExtra("location_name");

            setLocationDetails(locationName);
        }
    }


    //Set details from information passed in from RecyclerViewAdapter
    private void setLocationDetails(String locationName) {
        Log.d(TAG, "setLocationDetails: Setting Location name and details");

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ArrayList<Location> locations = databaseAccess.getLocations();
        int index = 0;
        for (Location location : locations) {
            String foundName = location.Name;
            if (foundName.equals(locationName)) {
                index = locations.indexOf(location);
            }
        }
        databaseAccess.close();

        Location loc = locations.get(index);

        TextView name = findViewById(R.id.location_name);
        name.setText(locationName);

        TextView type = findViewById(R.id.type_description);
        type.setText(loc.Type);

        TextView longitude = findViewById(R.id.longitude_description);
        longitude.setText(loc.Longitude + ", " + loc.Latitude);

        TextView address = findViewById(R.id.address_description);
        address.setText(loc.Address);

        TextView addr = findViewById(R.id.address_description);
        addr.setText(loc.Address);

        TextView phone = findViewById(R.id.phone_description);
        phone.setText(loc.Phone);

    }
}