package cs2340.group61.doughnation.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import cs2340.group61.doughnation.model.domain.accounts.User;

public class AppData {
    public static DatabaseReference dbUsers = FirebaseDatabase.getInstance().getReference().child("users");
    //public static DatabaseReference dbOrgs = FirebaseDatabase.getInstance().getReference().child("organizations");
    public static DatabaseReference dbLocations = FirebaseDatabase.getInstance().getReference().child("locations");
    public static DatabaseReference dbDonations = FirebaseDatabase.getInstance().getReference().child("donations");




    private static List<User> accounts = new ArrayList<User>();

    public static List<User> getAccounts() {
        return accounts;
    }

    public static void addAccount(User u) {
        accounts.add(u);
    }

    public static void deleteAccount(User u) {
        if (accounts.contains(u)) {
            accounts.remove(u);
        }
    }

    public static User findUserByEmail(String email) {
        for (User u : accounts) {
            if (email.equals(u.getEmail())) {
                return u;
            }
        }
        return null;
    }
}
