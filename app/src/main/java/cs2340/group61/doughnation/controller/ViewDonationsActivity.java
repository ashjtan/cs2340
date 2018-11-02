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
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

import cs2340.group61.doughnation.model.Location;
import cs2340.group61.doughnation.model.Utils;
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

        //Initializing UI views

        //Creating spinners for locations and categories to sort
        nameList.add("ALL LOCATIONS");

        //Creating database reference for donations and locations
        databaseDonations = FirebaseDatabase.getInstance().getReference("donations");
        databaseLocations = FirebaseDatabase.getInstance().getReference("locations");

        //Creating array for donation categories to fill category spinner
        ArrayList<String> cat_array = new ArrayList<String>();
        cat_array.add("NO CATEGORY SELECTED");
        cat_array.add("Clothing");
        cat_array.add("Electronics");
        cat_array.add("Kitchen");
        cat_array.add("Household");
        cat_array.add("Hat");
        cat_array.add("Other");

        //Creating spinner for categories and filling it
        ArrayAdapter<String> adapterCat = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, cat_array);
        adapterCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner catItems = (Spinner) findViewById(R.id.sort_category_spinner);
        catItems.setAdapter(adapterCat);

        //Button to go back to locationDetailActivity page
        Button backbutton = (Button) findViewById(R.id.back_view_location);

        //Button to logout
        Button logoutButton = (Button) findViewById(R.id.return_login_Button);

        //Button to sort recyclerview
        Button sortButton = (Button) findViewById(R.id.sort_button);

        //SearchView to sort RecyclerView
        final SearchView stringSearch = (SearchView) findViewById(R.id.string_searchView);

        //Creating onClick methods for views

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

        //Method to sort Donations on click SortButton
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                //Spinner for location Items
                Spinner locationItems = (Spinner) findViewById(R.id.sort_location_spinner);

                //New sorted arrays to go in recycler view
                donationDesc = new ArrayList<>();
                donationTitles = new ArrayList<>();

                //sorting logic
                for (Donation donation: donationList) {
//                    if (locationItems.getSelectedItem().toString().equals("ALL LOCATIONS")) {
//                        donationDesc.add(donation.timestamp + " - " + donation.shortdescription);
//                        donationTitles.add(donation.title);
//                    }
//                    else if (donation.location.equals(locationItems.getSelectedItem().toString())) {
//                        donationDesc.add(donation.timestamp + " - " + donation.shortdescription);
//                        donationTitles.add(donation.title);
//                    }
                    if (isLocationTrue(donation, locationItems.getSelectedItem().toString())
                            & isCategoryTrue(donation, catItems.getSelectedItem().toString())
                            & isSearchTrue(donation, stringSearch.getQuery())){
                        donationDesc.add(donation.timestamp + " - " + donation.shortdescription);
                        donationTitles.add(donation.title);
                    }
                }
                initRecyclerView();
                if (donationTitles.isEmpty()) {
                    Utils.showDialog("No valid donations match search criteria.", "No Results",
                            ViewDonationsActivity.this);
                }
              }
        });

    }

    //Helper method to see if location in spinner matches location of donation item in recyclerView
    private boolean isLocationTrue(Donation donation, String location_item) {
        if (location_item.equals("ALL LOCATIONS")
                || (donation.location.equals(location_item))) {
            return true;
        }
        return false;
    }

    //Helper method to see if category in spinner matches category of donation item in recyclerView
    private boolean isCategoryTrue(Donation donation, String category_item) {
        if (category_item.equals("NO CATEGORY SELECTED")
                || (donation.category.equals(category_item))) {
            return true;
        }
        return false;
    }

    //Helper method to see if string in searchView matches title of donation item in recyclerView
    private boolean isSearchTrue(Donation donation, CharSequence chars){
        String tempString = chars.toString();
        if (chars.equals(null)) {
            return true;
        }
        if (tempString.equals("") || donation.title.contains(chars)) {
            return true;
        }
        return false;
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
