package cs2340.group61.doughnation.model.domain.accounts;

import cs2340.group61.doughnation.model.AppData;
import cs2340.group61.doughnation.model.domain.AccountType;

public class GeneralUser extends User {

    //Default Constructor
    public GeneralUser() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.locked = false;
        this.acctType = AccountType.GENERAL_USER.toString();
    }

    //Overloaded Constructor
    public GeneralUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.locked = false;
        this.acctType = AccountType.GENERAL_USER.toString();
    }

    //construct GeneralUser + write to db
    public static void createGeneralUser(String name, String email, String password) {
        GeneralUser user = new GeneralUser(name, email, password);

        AppData.dbUsers.child(email).setValue(user);            //add user
    }
}
