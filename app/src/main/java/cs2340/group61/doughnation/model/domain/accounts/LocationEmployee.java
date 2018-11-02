package cs2340.group61.doughnation.model.domain.accounts;

import cs2340.group61.doughnation.model.AppData;
import cs2340.group61.doughnation.model.Location;
import cs2340.group61.doughnation.model.domain.AccountType;

public class LocationEmployee extends User {
    //TODO: add Location obj field
    private Location location;

    //Default Constructor
    public LocationEmployee() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.locked = false;
        this.acctType = AccountType.LOCATION_EMPLOYEE.toString();
        this.location = new Location();
    }

    //Overloaded Constructor
    public LocationEmployee(String name, String email, String password, Location location) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.locked = false;
        this.acctType = AccountType.LOCATION_EMPLOYEE.toString();
        this.location = location;
    }

    //construct LocationEmployee + write to db
    public void createAdmin(String name, String email, String password, Location location) {
        LocationEmployee locationEmployee = new LocationEmployee(name, email, password, location);

        AppData.dbUsers.child(email).setValue(locationEmployee);            //add user w/ id = email
    }
}

