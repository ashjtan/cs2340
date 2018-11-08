//This class creates a page of the location's details.
package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cs2340.group61.doughnation.controller.ViewLocationsActivity;

import cs2340.group61.doughnation.model.Location;

import cs2340.group61.doughnation.R;

public class LocationDetailsActivity extends AppCompatActivity {

    public ArrayList<Location> locationList = new ArrayList<>();
    public DatabaseReference databaseLocations;
    public String locationName;

    private static final String TAG = "LocationDetailActivity";

    /**
     * This is a method to create the location details page.
     * @param savedInstanceState The previously saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);

        databaseLocations = FirebaseDatabase.getInstance().getReference("locations");

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

    }

    /**
     * This is to display the page to the user.
     */
    @Override
    protected void onStart() {
        super.onStart();

        databaseLocations.addValueEventListener(new ValueEventListener() {

            /**
             * This method is to change the data.
             * @param dataSnapshot the data.
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locationList.clear();

                for(DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    Location location = locationSnapshot.getValue(Location.class);

                    locationList.add(location);
                }
                getIncomingIntent();
            }

            /**
             * Necessary for implementation; unused.
             * @param databaseError The database error.
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * This method is to stop displaying the page
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * This method is to get information passed in from RecyclerViewAdapter.
     */
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: Checking for incoming intent.");
        if(getIntent().hasExtra("location_name")) {
            Log.d(TAG, "getIncomingIntent: Found intent extras");

            String locationName = getIntent().getStringExtra("location_name");

            this.locationName = locationName;
            setLocationDetails(locationName);
        }
    }

    /**
     * This is a method to set details from information passed in from RecyclerViewAdapter.
     * @param locationName The name of the location.
     */
    private void setLocationDetails(String locationName) {
        Log.d(TAG, "setLocationDetails: Setting Location name and details");
        int index = 0;
        for (Location location: locationList) {
            if (location.name.equals(locationName)) {
                index = locationList.indexOf(location);
            }
        }
        Location loc = locationList.get(index);

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
