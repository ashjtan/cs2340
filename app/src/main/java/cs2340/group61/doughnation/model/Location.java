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

    /**
     * Sets the location ID.
     * @param id the ID of the location.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the name of the location.
     * @param name the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the latitude of the location.
     * @param latitude the latitude.
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * Sets the longitude of the location.
     * @param longitude the longitude.
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * Sets the address of the location.
     * @param address the street address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the city of the location.
     * @param city the city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the state of the location.
     * @param state the state.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Sets the zip code of the location.
     * @param zip the zip code.
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Sets the type of location.
     * @param type the type -- warehouse, donation center, etc.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the phone number of the location.
     * @param phone the phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the website of the location.
     * @param website the website.
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Gets the ID of the location.
     * @return the ID number as a String.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the location.
     * @return the name as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the latitude of the location.
     * @return the latitude as a String.
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude of the location.
     * @return the longitude as a String.
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Gets the street address of the location.
     * @return the street address as a String.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the city of the location.
     * @return the city as a String.
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the state of the location.
     * @return the state as a String.
     */
    public String getState() {
        return state;
    }

    /**
     * Gets the zip code of the location.
     * @return the zip code as a String.
     */
    public String getZip() {
        return zip;
    }

    /**
     * Gets the type of the location.
     * @return the type as a String.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the phone number of the location.
     * @return the phone number as a String.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the website of the location.
     * @return the website as a String.
     */
    public String getWebsite() {
        return website;
    }
}
