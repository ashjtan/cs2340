package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import cs2340.group61.doughnation.model.Location;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cs2340.group61.doughnation.R;

public class ViewLocationsActivity extends AppCompatActivity {

    private static final String TAG = "ViewLocationsActivity";

    //variables
    private ArrayList<String> locationNames = new ArrayList<>();
    public ArrayList<Location> locationList = new ArrayList<>();
    public DatabaseReference databaseLocations;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);

        databaseLocations = FirebaseDatabase.getInstance().getReference("locations");

        fillLocationNames();
        Log.d(TAG, "onCreate: Location Names added to array");

        //Back button to go to home screen
        Button toMain = findViewById(R.id.back_Home_button);

        //Back button to go to login screen
        Button logout = findViewById(R.id.return_login_Button);

        //Method to return to homeScreen
        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewLocationsActivity.this,
                        HomePageActivity.class));
            }
        });

        //Method to logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewLocationsActivity.this,
                        LoginActivity.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        databaseLocations.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locationList.clear();

                for(DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    Location location = locationSnapshot.getValue(Location.class);

                    locationList.add(location);
                }

                fillLocationNames();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    //Method to fill recycler view with names
    private void fillLocationNames() {
        for (Location location: locationList) {
            locationNames.add(location.name);
        }
        initRecyclerView();
    }

    //Method to set up RecyclerView
    private void initRecyclerView() {
        RecyclerView recyclerview = findViewById(R.id.mylocations_View);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(locationNames, this);
        recyclerview.setAdapter(adapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
    }

    protected ArrayList<Location> getLocationList() {
        return locationList;
    }
}
