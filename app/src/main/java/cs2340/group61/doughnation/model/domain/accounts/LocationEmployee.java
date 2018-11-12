package cs2340.group61.doughnation.model.domain.accounts;

/**
 * Represents a location employee object.
 */
public class LocationEmployee extends User {
//TODO: add Location obj field

    /**
     * Creates a location employee object.
     */
    public LocationEmployee() {
        super();
    }

    /**
     * Creates a location employee object with parameters.
     * @param name the name of the location employee.
     * @param email The employee's email.
     * @param password The password to the account.
     */
    public LocationEmployee(String name, String email, String password) {
        super(name, email, password);
    }
}
