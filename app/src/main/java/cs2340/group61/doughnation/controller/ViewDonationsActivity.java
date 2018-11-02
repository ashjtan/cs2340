package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

import cs2340.group61.doughnation.model.AppData;
import cs2340.group61.doughnation.model.domain.DonationCategory;
import cs2340.group61.doughnation.model.domain.Location;
import cs2340.group61.doughnation.model.domain.Donation;


import java.util.ArrayList;
import java.util.List;

import cs2340.group61.doughnation.R;

public class ViewDonationsActivity extends AppCompatActivity {

    private static final String TAG = "ViewDonationsActivity";

    private ArrayList<String> donationDesc = new ArrayList<>();
    private ArrayList<String> donationTitles = new ArrayList<>();
    public ArrayList<Donation> donationList = new ArrayList<>();
    public ArrayList<Location> locationList = new ArrayList<>();
    public ArrayList<String> locationNameList = new ArrayList<>();

    private Button btnLogout;
    private Button btnBack;
    private Button btnSort;
    private Spinner donationCategories;
    private Spinner locations;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donations);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnSort = (Button) findViewById(R.id.btnSort);

        donationCategories = (Spinner) findViewById(R.id.categoryFilter);
        locations = (Spinner) findViewById(R.id.locationFilter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        genDonations();
        genLocations();

        genDonationCategories();

        //generate donation categories spinner
        ArrayAdapter<String> donationCategoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, genDonationCategories());
        donationCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        donationCategories.setAdapter(donationCategoryAdapter);

        //generate locations spinner
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(ViewDonationsActivity.this, android.R.layout.simple_spinner_item, locationNameList);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locations = (Spinner) findViewById(R.id.locationFilter);
        locations.setAdapter(locationAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }




    //EVENT HANDLERS
    public void switchToLogin(View v) {             //switches to login page
        startActivity(new Intent(ViewDonationsActivity.this, LoginActivity.class));
    }

    public void switchToHome(View v) {              //switches to home page
        startActivity(new Intent(ViewDonationsActivity.this, HomePageActivity.class));
    }

    public void handleSort(View v) {
        //New sorted arrays to go in recycler view
        donationDesc = new ArrayList<>();
        donationTitles = new ArrayList<>();

        //sorting logic
        for (Donation donation: donationList) {
            if (isInCategory(donation) && isAtLocation(donation)) {
                donationDesc.add(donation.timestamp + " - " + donation.shortdescription);
                donationTitles.add(donation.title);
            }
        }
        initDonationsView();
    }





    //HELPER METHODS
    private boolean isInCategory(Donation d) {
        return donationCategories.getSelectedItem().toString().isEmpty() ||
                donationCategories.getSelectedItem().toString().equalsIgnoreCase(d.category);
    }

    private boolean isAtLocation(Donation d) {
        return true;
    }

    private String[] genDonationCategories() {
        ArrayList<String> categoriesForSpinner = new ArrayList<>();
        categoriesForSpinner.add("");

        DonationCategory[] donationCategories = DonationCategory.values();
        for (DonationCategory dc: donationCategories) {
            categoriesForSpinner.add(dc.toString());
        }
        return categoriesForSpinner.toArray(new String[DonationCategory.values().length + 1]);
    }

    private void genLocations() {
        AppData.dbLocations.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locationList.clear();
                for(DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    locationList.add(locationSnapshot.getValue(Location.class));
                }

                for (Location lo: locationList) {
                    locationNameList.add(lo.name);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void genDonations() {
        AppData.dbDonations.addValueEventListener(new ValueEventListener() {
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
    }




    //INITIALIZE METHODS
    public void initDonationsView(){
        RecyclerView recyclerView = findViewById(R.id.donationsView);
        DonationViewAdapter adapter = new DonationViewAdapter(this, donationDesc, donationTitles);
        adapter.updateList(donationDesc,donationTitles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initDonationDescriptions() {
        for (Donation donation: donationList) {
            donationDesc.add(donation.timestamp + " - " + donation.shortdescription);
            donationTitles.add(donation.title);
        }
        initDonationsView();
    }
}
