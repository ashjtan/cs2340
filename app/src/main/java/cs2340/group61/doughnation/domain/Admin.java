package cs2340.group61.doughnation.domain;

/**
 * Can add/remove users, add/remove locations, lock/unlock accounts.
 */
public class Admin {
    //Fields
    private String name;
    private String email;
    private String password;

    //Default Constructor
    public Admin() {
        this.name = "";
        this.email = "";
        this.password = "";
    }

    //Overloaded Constructor
    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
