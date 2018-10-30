package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cs2340.group61.doughnation.model.Location;

import cs2340.group61.doughnation.R;

public class LocationDetailsActivity extends AppCompatActivity {

    private static final String TAG = "LocationDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);

        //Create button to go back to location list screen
        Button backButton = (Button) findViewById(R.id.back_location_button);

        //Create button to logout
        Button logout = (Button) findViewById(R.id.return_login_Button);

//        //Button to view Donation List
//        Button viewDonations = (Button) findViewById(R.id.view_donations_button);

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

        //Click listener to go to view donations page
//        viewDonations.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LocationDetailsActivity.this,
//                        ViewDonationsActivity.class));
//            }
//        });
        
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

        Location loc = new Location();

        TextView name = findViewById(R.id.location_name);
        name.setText(locationName.toUpperCase());

        TextView type = findViewById(R.id.type_description);
        type.setText(loc.type);

        TextView longitude = findViewById(R.id.longitude_description);
        longitude.setText(loc.longitude + ", " + loc.latitude);

        TextView address = findViewById(R.id.address_description);
        address.setText(loc.address);

        TextView addr = findViewById(R.id.address_description);
        addr.setText(loc.address);

        TextView phone = findViewById(R.id.phone_description);
        phone.setText(loc.phone);

    }
}
