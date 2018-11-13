package cs2340.group61.doughnation.model;

/**
 * Represents a location object.
 */
@SuppressWarnings("unused")
public class Location {
    private String id;
    private String name;
    private String latitude;
    private String longitude;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String type;
    private String phone;
    private String website;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }
}
