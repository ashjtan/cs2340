package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

        Log.d(TAG, "onCreate: Started");

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

        //Method to return to homeScreen
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
        locationNames.add("Nantucket");
        locationNames.add("Canada");
        locationNames.add("London");
        locationNames.add("Alabama");
        locationNames.add("California");
        locationNames.add("Romania");
        locationNames.add("Thailand");
        locationNames.add("Japan");
        locationNames.add("Paris");
        locationNames.add("Seven Eleven");
        locationNames.add("Taco Bell");
        locationNames.add("The Louvre");
        locationNames.add("Eiffel Tower");

        initRecyclerView();
    }

    //Method to set up RecyclerView
    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recycler view");
        RecyclerView recyclerview = findViewById(R.id.mylocations_View);
        Log.d(TAG, "initRecyclerView: recyclerview initiated");
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(locationNames, this);
        Log.d(TAG, "initRecyclerView: Recyclerview adapter initiated");
        recyclerview.setAdapter(adapter);
        Log.d(TAG, "initRecyclerView: Recyclerview adapter set");
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        Log.d(TAG, "initRecyclerView: Layout manager created");
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Log.d(TAG, "initRecyclerView: orientation set");
        recyclerview.setLayoutManager(layoutManager);
        Log.d(TAG, "initRecyclerView: layout manager set");
    }
}
