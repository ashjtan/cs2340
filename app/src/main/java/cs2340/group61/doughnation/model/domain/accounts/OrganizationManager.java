package cs2340.group61.doughnation.model.domain.accounts;

import cs2340.group61.doughnation.model.AppData;
import cs2340.group61.doughnation.model.domain.AccountType;
import cs2340.group61.doughnation.model.domain.Organization;

public class OrganizationManager extends User {
    String organization;

    //Default Constructor
    public OrganizationManager() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.locked = false;
        this.acctType = AccountType.ORGANIZATION_MANAGER.toString();
        this.organization = "";
    }

    //Overloaded Constructor
    public OrganizationManager(String name, String email, String password, Organization organization) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.locked = false;
        this.acctType = AccountType.ORGANIZATION_MANAGER.toString();
        this.organization = organization.getName();
    }

    //construct GeneralUser + write to db
    protected void createOrgManager(String name, String email, String password, Organization organization) {
        OrganizationManager orgManager = new OrganizationManager(name, email, password, organization);

        AppData.dbUsers.child(email).setValue(orgManager);            //add user
    }
}
