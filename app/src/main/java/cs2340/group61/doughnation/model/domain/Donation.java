package cs2340.group61.doughnation.model.domain;

/**
 * This class represents a donation object.
 */
public class Donation {
    private String title;
    private String timestamp;
    private String location;
    private String shortDescription;
    private String fullDescription;
    private String value;
    private String category;

//    /**
//     * Setter method to set the id.
//     * @param id The id number.
//     */
//    public void setId(String id) {
//        String id1 = id;
//    }

    /**
     * Sets the title of the donation.
     * @param title The name of the donation.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the timestamp of the object.
     * @param timestamp The time at which the object was added.
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Sets the location of the donation.
     * @param location the location of the donation.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets the short description of the donation.
     * @param shortDescription The abbreviated version of the description.
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Sets the full description of the donation.
     * @param fullDescription The entire description.
     */
    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    /**
     * Sets the value of the donation.
     * @param value The worth of the donation.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Sets the category of the donation.
     * @param category The category under which the donation falls.
     */
    public void setCategory(String category) {
        this.category = category;
    }

// --Commented out by Inspection START (11/8/2018 12:31 AM):
//    public String getId() {
//        return id;
//    }
// --Commented out by Inspection STOP (11/8/2018 12:31 AM)

    /**
     * Returns the title of the donation.
     * @return the name.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the time stamp of the donation.
     * @return the time at which it was added.
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the location of the donation.
     * @return the location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the short description.
     * @return the short description.
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Returns the full description.
     * @return the full description.
     */
    @SuppressWarnings("TypeMayBeWeakened")
    public String getFullDescription() {
        return fullDescription;
    }

    /**
     * returns the value of the donation.
     * @return the worth of the item.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the category of the donation.
     * @return the category.
     */
    public String getCategory() {
        return category;
    }
}