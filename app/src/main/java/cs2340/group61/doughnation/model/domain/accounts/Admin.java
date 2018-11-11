package cs2340.group61.doughnation.model.domain.accounts;

/**
 * Can add/remove users, add/remove locations, lock/unlock accounts.
 */
@SuppressWarnings("unused")
public class Admin extends User {
    /**
     * This method creates a new Admin object.
     */
    public Admin() {
        super();
    }

    /**
     * This method creates a new Admin object with parameters.
     * @param name The name of the admin.
     * @param email The email of the admin.
     * @param password The password to the account.
     */
    public Admin(String name, String email, String password) {
        super(name, email, password);
    }
}
