package cs2340.group61.doughnation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cs2340.group61.doughnation.model.domain.Donation;

import java.util.ArrayList;
import java.util.List;
import cs2340.group61.doughnation.model.Location;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DatabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Insert a location into the database.
     *
     * @param location the location to be inserted
     */
    public void insertLocation(Location location) {
        ContentValues values = new ContentValues();
        values.put("Name", location.Name);
        values.put("Latitude", location.Latitude);
        values.put("Longitude", location.Longitude);
        values.put("Address", location.Address);
        values.put("City", location.City);
        values.put("State", location.State);
        values.put("Zip", location.Zip);
        values.put("Type", location.Type);
        values.put("Phone", location.Phone);
        values.put("Website", location.Website);
        database.insert("locations", null, values);
    }

    /**
     * Insert a donation into the database.
     *
     * @param donation the donation to be inserted
     */
    public void insertDonation(Donation donation) {
        ContentValues values = new ContentValues();
        values.put("title", donation.title);
        values.put("timestamp", donation.timestamp);
        values.put("location", donation.location);
        values.put("shortDescription", donation.shortDescription);
        values.put("fullDescription", donation.fullDescription);
        values.put("value", donation.value);
        values.put("category", donation.category);
        values.put("comments", donation.comments);
        values.put("organization", donation.organization);
        database.insert("donations", null, values);
    }

    /**
     * Read all locations from the database.
     *
     * @return a List of locations
     */
    public ArrayList<Location> getLocations() {
        ArrayList<Location> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM locations", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Location location = new Location();
            location.setName(cursor.getString(1));
            location.setLatitude(cursor.getString(2));
            location.setLongitude(cursor.getString(3));
            location.setAddress(cursor.getString(4));
            location.setCity(cursor.getString(5));
            location.setState(cursor.getString(6));
            location.setZip(cursor.getString(7));
            location.setType(cursor.getString(8));
            location.setPhone(cursor.getString(9));
            location.setWebsite(cursor.getString(10));
            list.add(location);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    /**
     * Read all Donations from the database.
     *
     * @return a List of Donations
     */
    public ArrayList<Donation> getDonations() {
        ArrayList<Donation> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM donations", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Donation donation = new Donation();
            donation.setTitle(cursor.getString(1));
            donation.setTimestamp(cursor.getString(2));
            donation.setLocation(cursor.getString(3));
            donation.setShortDescription(cursor.getString(4));
            donation.setFullDescription(cursor.getString(5));
            donation.setValue(cursor.getString(6));
            donation.setCategory(cursor.getString(7));
            donation.setComments(cursor.getString(8));
            donation.setOrganization(cursor.getString(9));
            list.add(donation);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    /**
     * Update the Location details.
     *
     * @param oldLocation the old contact to be replaced
     * @param newLocation the new contact to replace
     */
    public void updateLocation(Location oldLocation, Location newLocation) {
        ContentValues values = new ContentValues();
        values.put("Name", newLocation.Name);
        values.put("Latitude", newLocation.Latitude);
        values.put("Longitude", newLocation.Longitude);
        values.put("Address", newLocation.Address);
        values.put("City", newLocation.City);
        values.put("State", newLocation.State);
        values.put("Zip", newLocation.Zip);
        values.put("Type", newLocation.Type);
        values.put("Phone", newLocation.Phone);
        values.put("Website", newLocation.Website);
        database.update("locations", values, "Name = ?", new String[]{oldLocation.Name});
    }

    /**
     * Update the Donation details.
     *
     * @param oldDonation the old donation to be replaced
     * @param newDonation the new donation to replace
     */
    public void updateDonation(Donation oldDonation, Donation newDonation) {
        ContentValues values = new ContentValues();
        values.put("title", newDonation.title);
        values.put("timestamp", newDonation.timestamp);
        values.put("location", newDonation.location);
        values.put("shortDescription", newDonation.shortDescription);
        values.put("fullDescription", newDonation.fullDescription);
        values.put("value", newDonation.value);
        values.put("category", newDonation.category);
        values.put("comments", newDonation.comments);
        values.put("organization", newDonation.organization);
        database.update("donations", values, "title = ?", new String[]{oldDonation.title});
    }

    /**
     * Delete the provided Location.
     *
     * @param location the Location to delete
     */
    public void deleteLocation(Location location) {
        database.delete("locations", "Name = ?", new String[]{location.Name});
        database.close();
    }

    /**
     * Delete the provided Donation.
     *
     * @param donation the Donation to delete
     */
    public void deleteDonation(Donation donation) {
        database.delete("donations", "title = ?", new String[]{donation.title});
        database.close();
    }
}