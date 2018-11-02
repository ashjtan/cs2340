package cs2340.group61.doughnation.model.domain.accounts;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cs2340.group61.doughnation.model.domain.AccountType;

/**
 * General user that can view locations and location info.
 * Parent class to specific account types with more application options/abilities.
 */
public abstract class User {
    //Fields
    //protected String id;
    protected String name;
    protected String email;
    protected String password;
    protected boolean locked;
    protected String acctType;

/*    //Default Constructor
    public User() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.locked = false;
        this.acctType = AccountType.GENERAL_USER.toString();
    }

    //Overloaded Constructor
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.locked = false;
        this.acctType = AccountType.GENERAL_USER.toString();
    }*/

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getAcctType() {
        return acctType;
    }
}
