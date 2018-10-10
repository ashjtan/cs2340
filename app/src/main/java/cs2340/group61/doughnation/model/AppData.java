package cs2340.group61.doughnation.model;

import java.util.ArrayList;
import java.util.List;

import cs2340.group61.doughnation.model.domain.accounts.User;

public class AppData {
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
