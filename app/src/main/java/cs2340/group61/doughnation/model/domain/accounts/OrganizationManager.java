package cs2340.group61.doughnation.model.domain.accounts;

/**
 * Represents an organization manager object.
 */
public class OrganizationManager extends User {
    /**
     * Creates an organization manager object.
     */
    public OrganizationManager() {
        super();
    }

    /**
     * Creates an organization manager object with parameters.
     * @param name The name of the employee.
     * @param email The email of the employee.
     * @param password The password to the account.
     */
    public OrganizationManager(String name, String email, String password) {
        super(name, email, password);
    }
}
