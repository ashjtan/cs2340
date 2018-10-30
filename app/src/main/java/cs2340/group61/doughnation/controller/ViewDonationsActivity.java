package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

import cs2340.group61.doughnation.model.Location;
import cs2340.group61.doughnation.model.domain.Donation;


import java.util.ArrayList;

import cs2340.group61.doughnation.R;

public class ViewDonationsActivity extends AppCompatActivity {

    private static final String TAG = "ViewDonationsActivity";

    //Temp arrays that hold example titles and descriptions
    //to go in donation recycler view at each location. These
    //should be replaced with database
    private ArrayList<String> donationDesc = new ArrayList<>();
    private ArrayList<String> donationTitles = new ArrayList<>();
    public ArrayList<Donation> donationList = new ArrayList<>();
    public ArrayList<Location> locationList = new ArrayList<>();
    public ArrayList<String> nameList = new ArrayList<>();
    public DatabaseReference databaseDonations;
    public DatabaseReference databaseLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donations);

        nameList.add("NO LOCATION SELECTED");

        databaseDonations = FirebaseDatabase.getInstance().getReference("donations");
        databaseLocations = FirebaseDatabase.getInstance().getReference("locations");

        ArrayList<String> cat_array = new ArrayList<String>();
        cat_array.add("NO CATEGORY SELECTED");
        cat_array.add("Clothing");
        cat_array.add("Electronics");
        cat_array.add("Kitchen");
        cat_array.add("Household");
        cat_array.add("Hat");
        cat_array.add("Other");

        ArrayAdapter<String> adapterCat = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, cat_array);
        adapterCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner catItems = (Spinner) findViewById(R.id.sort_category_spinner);
        catItems.setAdapter(adapterCat);

        Spinner locationItems = (Spinner) findViewById(R.id.sort_location_spinner);

        //Button to go back to locationDetailActivity page
        Button backbutton = (Button) findViewById(R.id.back_view_location);

        //Button to logout
        Button logoutButton = (Button) findViewById(R.id.return_login_Button);

        Button sortButton = (Button) findViewById(R.id.sort_button);

        //Method to go back to LocationDetailActivity
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewDonationsActivity.this,
                        HomePageActivity.class));
            }
        });

        //Method to logout
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewDonationsActivity.this,
                        LoginActivity.class));
            }
        });


        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                Spinner locationItems = (Spinner) findViewById(R.id.sort_location_spinner);
                donationDesc = new ArrayList<>();
                donationTitles = new ArrayList<>();
                for (Donation donation: donationList) {
                    if (donation.location.equals(locationItems.getSelectedItem().toString())) {
                        donationDesc.add(donation.timestamp + " - " + donation.shortdescription);
                        donationTitles.add(donation.title);
                    }
                }
                initRecyclerView();
              }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseDonations.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donationList.clear();

                for(DataSnapshot donationSnapshot : dataSnapshot.getChildren()) {
                    Donation donation = donationSnapshot.getValue(Donation.class);

                    donationList.add(donation);
                }

                initDonationDescriptions();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseLocations.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locationList.clear();

                for(DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    Location location = locationSnapshot.getValue(Location.class);

                    locationList.add(location);
                }

                for (Location lo: locationList) {
                    nameList.add(lo.name);
                }


                ArrayAdapter<String> adapterLoc = new ArrayAdapter<String>(ViewDonationsActivity.this, android.R.layout.simple_spinner_item, nameList);
                adapterLoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner locationItems = (Spinner) findViewById(R.id.sort_location_spinner);
                locationItems.setAdapter(adapterLoc);

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


    //Using this method to fill up my temporary array that holds Donation Descriptions
    //These faux descriptions should be replaced with actual data
    private void initDonationDescriptions() {

        for (Donation donation: donationList) {
            donationDesc.add(donation.timestamp + " - " + donation.shortdescription);
            donationTitles.add(donation.title);
        }
            initRecyclerView();
    }

    //Method to set up RecyclerView
    public void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.donations_View);
        DonationViewAdapter adapter = new DonationViewAdapter(this, donationDesc,
                donationTitles);
        adapter.updateList(donationDesc,donationTitles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
