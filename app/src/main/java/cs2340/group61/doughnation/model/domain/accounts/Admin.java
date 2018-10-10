package cs2340.group61.doughnation.model.domain.accounts;

/**
 * Can add/remove users, add/remove locations, lock/unlock accounts.
 */
public class Admin extends User {
    public Admin() {
        super();
    }

    public Admin(String name, String email, String password) {
        super(name, email, password);
    }
}
