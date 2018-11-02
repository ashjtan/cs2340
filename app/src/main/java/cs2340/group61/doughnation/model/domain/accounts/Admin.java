package cs2340.group61.doughnation.model.domain.accounts;

import cs2340.group61.doughnation.model.domain.AccountType;
import cs2340.group61.doughnation.model.AppData;
/**
 * Can add/remove users, add/remove locations, lock/unlock accounts.
 */
public class Admin extends User {
    //Default Constructor
    public Admin() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.locked = false;
        this.acctType = AccountType.ADMIN.toString();
    }

    //Overloaded Constructor
    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.locked = false;
        this.acctType = AccountType.ADMIN.toString();
    }

    //construct Admin + write to db
    public static void createAdmin(String name, String email, String password) {
        Admin admin = new Admin(name, email, password);

        AppData.dbUsers.child(email).setValue(admin);            //add user w/ id = email
    }

}
