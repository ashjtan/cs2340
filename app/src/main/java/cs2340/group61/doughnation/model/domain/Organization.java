package cs2340.group61.doughnation.model.domain;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import cs2340.group61.doughnation.model.AppData;
import cs2340.group61.doughnation.model.Location;
import cs2340.group61.doughnation.model.domain.accounts.GeneralUser;
import cs2340.group61.doughnation.model.domain.accounts.OrganizationManager;

public class Organization {

    String name;
    List<Location> locations;
    OrganizationManager manager;

    public Organization() {
        this.name = "";
        this.locations = new ArrayList<Location>();
        this.manager = new OrganizationManager();
    }

    public Organization(String name, List<Location> locations, OrganizationManager manager) {
        this.name = name;
        this.locations = locations;
        this.manager = manager;
    }

/*    //construct Organization + write to db
    protected void createOrganiztion(String name, List<Location> locations, OrganizationManager manager) {
        Organization org = new Organization(name, locations, manager);

        AppData.dbOrgs.child(name).setValue(org);            //add organization
    }*/


    public String getName() {
        return name;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public OrganizationManager getManager() {
        return manager;
    }
}
