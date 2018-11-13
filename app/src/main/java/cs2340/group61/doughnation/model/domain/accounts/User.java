package cs2340.group61.doughnation.model.domain.accounts;

/**
 * General user that can view locations and location info.
 * Parent class to specific account types with more application options/abilities.
 */
@SuppressWarnings("ALL")
public class User {
    //Fields
    protected String id;
    private final String name;
    private final String email;
    private final String password;
    private final boolean locked;

    //Default Constructor
    User() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.locked = false;
    }

    /**
     * Creates a user object with parameters.
     * @param name The name of the user.
     * @param email The email of the user.
     * @param password The password to the account.
     */
    //Overloaded Constructor
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.locked = false;
    }

    /**
     * Retrieves the name of the user.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the email of the user.
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the password to the account.
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Identifies whether the account is locked.
     * @return a boolean -- true if the account is locked; false otherwise.
     */
    public boolean isLocked() {
        return locked;
    }
}
