package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import cs2340.group61.doughnation.DatabaseAccess;
import cs2340.group61.doughnation.model.Location;

import java.util.ArrayList;

import cs2340.group61.doughnation.R;

public class ViewLocationsActivity extends AppCompatActivity {

    private static final String TAG = "ViewLocationsActivity";

    //variables
    private ArrayList<String> locationNames = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);

        fillLocationNames();
        Log.d(TAG, "onCreate: Location Names added to array");

        //Back button to go to home screen
        Button toMain = (Button) findViewById(R.id.back_Home_button);

        //Back button to go to login screen
        Button logout = (Button) findViewById(R.id.return_login_Button);

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

    //Method to fill recycler view with names
    private void fillLocationNames() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ArrayList<Location> locations = databaseAccess.getLocations();
        for (Location location : locations) {
            locationNames.add(location.Name);
        }
        databaseAccess.close();

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
}
