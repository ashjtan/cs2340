package cs2340.group61.doughnation.domain;

/**
 * General user that can view locations and location info.
 * Parent class to specific account types with more application options/abilities.
 */
public class User {
    //Fields
    protected String name;
    protected String email;
    protected String password;
    protected boolean locked;

    //Default Constructor
    public User() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.locked = false;
    }

    //Overloaded Constructors
    public User(String name, String email, String password, boolean locked) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.locked = locked;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.locked = false;
    }
}
