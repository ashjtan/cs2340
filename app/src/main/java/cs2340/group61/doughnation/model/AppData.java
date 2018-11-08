package cs2340.group61.doughnation.model;

import java.util.ArrayList;
import java.util.List;

import cs2340.group61.doughnation.model.domain.accounts.User;

@SuppressWarnings("ALL")
public class AppData {
    private static final List<User> accounts = new ArrayList<>();

    public static List<User> getAccounts() {
        return accounts;
    }

    public static void addAccount(User u) {
        accounts.add(u);
    }

    @SuppressWarnings("unused")
    public static void deleteAccount(User u) {
        accounts.remove(u);
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
