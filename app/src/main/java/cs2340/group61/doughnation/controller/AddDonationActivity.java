package cs2340.group61.doughnation.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cs2340.group61.doughnation.model.domain.Donation;
import cs2340.group61.doughnation.model.Location;

import java.util.ArrayList;

import cs2340.group61.doughnation.R;

public class AddDonationActivity extends AppCompatActivity {

    private static final String TAG = "ViewDonationsActivity";
    private DatabaseReference databaseLocations;
    private DatabaseReference databaseDonations;
    private ArrayList<Location> locationList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        databaseLocations = FirebaseDatabase.getInstance().getReference("locations");
        databaseDonations = FirebaseDatabase.getInstance().getReference("donations");

        //Arraylist to fill Locations spinner
        //Bri add code here

        //Arraylist to fill categories spinner
        ArrayList<String> cat_array = new ArrayList<String>();
        cat_array.add("Clothing");
        cat_array.add("Electronics");
        cat_array.add("Kitchen");
        cat_array.add("Household");
        cat_array.add("Hat");
        cat_array.add("Other");

        Spinner location = (Spinner) findViewById(R.id.location_selection_spinner);
        EditText name = (EditText) findViewById(R.id.add_name);
        EditText value = (EditText) findViewById(R.id.add_value);
        Spinner type = (Spinner) findViewById(R.id.donation_type_spinner);
        EditText description = (EditText) findViewById(R.id.add_description);

        ArrayAdapter<String> adapterCat = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, cat_array);
        adapterCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner catItems = (Spinner) findViewById(R.id.donation_type_spinner);
        catItems.setAdapter(adapterCat);

        //onStart();

        //for (Location lo: locationList) {
        //    nameList.add(lo.name);
        //}


        //ArrayAdapter<String> adapterLoc = new ArrayAdapter<String>(
        //        this, android.R.layout.simple_spinner_item, nameList);
        //adapterLoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Spinner locationItems = (Spinner) findViewById(R.id.location_selection_spinner);
        //locationItems.setAdapter(adapterLoc);


        //Button to go back to locationDetailActivity page
        Button backbutton = (Button) findViewById(R.id.back_button);

        //Button to logout
        Button logoutButton = (Button) findViewById(R.id.return_login_Button);

        //Button to go to AddDonationActivity
        Button addDonationButton = (Button) findViewById(R.id.done_button);



        //Method to go back to LocationDetailActivity
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddDonationActivity.this,
                        HomePageActivity.class));
            }
        });

        //Method to logout
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddDonationActivity.this,
                        LoginActivity.class));
            }
        });

        //Method to add donation
        addDonationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDonation();
                //logic here to set credentials and add info to database
                startActivity(new Intent(AddDonationActivity.this,
                        HomePageActivity.class));
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

                for (Location lo: locationList) {
                    nameList.add(lo.name);
                }


                ArrayAdapter<String> adapterLoc = new ArrayAdapter<String>(AddDonationActivity.this, android.R.layout.simple_spinner_item, nameList);
                adapterLoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner locationItems = (Spinner) findViewById(R.id.location_selection_spinner);
                locationItems.setAdapter(adapterLoc);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void addDonation() {
        String id = databaseDonations.push().getKey();

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String time = sdf.format(timestamp);

        Spinner locationItems = (Spinner) findViewById(R.id.location_selection_spinner);
        EditText name = (EditText) findViewById(R.id.add_name);
        EditText value = (EditText) findViewById(R.id.add_value);
        Spinner type = (Spinner) findViewById(R.id.donation_type_spinner);
        EditText description = (EditText) findViewById(R.id.add_description);

        Donation donation = new Donation();
        donation.setTitle(name.getText().toString());
        donation.setLocation(locationItems.getSelectedItem().toString());
        donation.setCategory(type.getSelectedItem().toString());
        donation.setFulldescription(description.getText().toString());
        donation.setShortdescription(description.getText().toString());
        donation.setValue(value.getText().toString());
        donation.setTimestamp(time);
        donation.setId(id);

        databaseDonations.child(id).setValue(donation);
    }
}
