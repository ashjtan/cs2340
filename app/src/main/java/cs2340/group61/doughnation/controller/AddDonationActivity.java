//This is a class to add a donation to the list of donations.
package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import cs2340.group61.doughnation.R;
import cs2340.group61.doughnation.model.Location;
import cs2340.group61.doughnation.model.Utils;
import cs2340.group61.doughnation.model.domain.Donation;

public class AddDonationActivity extends AppCompatActivity {

    private DatabaseReference databaseLocations;
    private DatabaseReference databaseDonations;
    private ArrayList<Location> locationList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();

    /**
     * This method is what the add donation page looks like upon creation.
     * @param savedInstanceState the object's previously saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        databaseLocations = FirebaseDatabase.getInstance().getReference("locations");
        databaseDonations = FirebaseDatabase.getInstance().getReference("donations");

        //ArrayList to fill Locations spinner
        //Bri add code here

        //ArrayList to fill categories spinner
        ArrayList<String> cat_array = new ArrayList<>();
        cat_array.add("Clothing");
        cat_array.add("Electronics");
        cat_array.add("Kitchen");
        cat_array.add("Household");
        cat_array.add("Hat");
        cat_array.add("Other");

//        Variables we never used
//        Spinner location = findViewById(R.id.location_selection_spinner);
//        EditText name = findViewById(R.id.add_name);
//        EditText value = findViewById(R.id.add_value);
//        EditText description = findViewById(R.id.add_description);

        ArrayAdapter<String> adapterCat = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, cat_array);
        adapterCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner catItems = findViewById(R.id.donation_type_spinner);
        catItems.setAdapter(adapterCat);

        //Button to go back to locationDetailActivity page
        Button backButton = findViewById(R.id.back_button);

        //Button to logout
        Button logoutButton = findViewById(R.id.return_login_Button);

        //Button to go to AddDonationActivity
        Button addDonationButton = findViewById(R.id.done_button);

        //Method to go back to LocationDetailActivity
        backButton.setOnClickListener(new View.OnClickListener() {
            /**
             * This is a method to go back to LocationDetailActivity.
             * @param v The page itself.
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddDonationActivity.this,
                        HomePageActivity.class));
            }
        });

        //Method to logout
        logoutButton.setOnClickListener(new View.OnClickListener() {
            /**
             * This is a method to logout.
             * @param v The page.
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddDonationActivity.this,
                        LoginActivity.class));
            }
        });

        //Method to add donation
        addDonationButton.setOnClickListener(new View.OnClickListener() {
            /**
             * This is a method to add a donation.
             * @param v The page.
             */
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.add_name);
                EditText value = findViewById(R.id.add_value);
                EditText description = findViewById(R.id.add_description);
                if (validDonation(name, value, description)) {
                    addDonation();
                    startActivity(new Intent(AddDonationActivity.this,
                            HomePageActivity.class));
                } else {
                    Utils.showDialog("Empty or invalid inputs somewhere.", "Invalid Donation", AddDonationActivity.this);
                }
                //logic here to set credentials and add info to database
                //startActivity(new Intent(AddDonationActivity.this,
                //        HomePageActivity.class));
            }
        });
    }

    /**
     * This method is what the page looks like when it becomes visible to the user.
     */
    @Override
    protected void onStart() {
        super.onStart();

        databaseLocations.addValueEventListener(new ValueEventListener() {

            /**
             * This method is to change data.
             * @param dataSnapshot The data.
             */
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
                Spinner locationItems = findViewById(R.id.location_selection_spinner);
                locationItems.setAdapter(adapterLoc);
            }

            @Override
            /**
             * Necessary for implementation; unused.
             * @param databaseError The database error.
             */
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * This method allows users to enter information in order to create a donation.
     */
    public void addDonation() {
        String id = databaseDonations.push().getKey();

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String time = sdf.format(timestamp);

        Spinner locationItems = findViewById(R.id.location_selection_spinner);
        EditText name = findViewById(R.id.add_name);
        EditText value = findViewById(R.id.add_value);
        Spinner type = findViewById(R.id.donation_type_spinner);
        EditText description = findViewById(R.id.add_description);

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

    /**
     * This is a helper method to check whether the information that the user enters for the donation is valid.
     * @param name The name of the donation.
     * @param value The price of the donation.
     * @param description The description of the donation.
     * @return A boolean -- true if the donation information is valid; false otherwise.
     */
    private boolean validDonation(EditText name, EditText value, EditText description) {
        String nameInput = name.getText().toString();
        String valueInput = value.getText().toString();
        String descriptionInput = description.getText().toString();

        boolean validName = (!nameInput.isEmpty());
        boolean validLength = valueInput.length() > 4;
        boolean validValue = valueInput.contains(".");
        boolean validDescription = (!descriptionInput.isEmpty());
        return validName && validLength && validValue && validDescription;
    }
}
