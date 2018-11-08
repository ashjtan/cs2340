package cs2340.group61.doughnation.model.domain;

public class Donation {
    private String id;
    private String title;
    private String timestamp;
    private String location;
    private String shortDescription;
    private String fullDescription;
    private String value;
    private String category;

    public Donation() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCategory(String category) {
        this.category = category;
    }

// --Commented out by Inspection START (11/8/2018 12:31 AM):
//    public String getId() {
//        return id;
//    }
// --Commented out by Inspection STOP (11/8/2018 12:31 AM)

    public String getTitle() {
        return title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLocation() {
        return location;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    @SuppressWarnings("TypeMayBeWeakened")
    public String getFullDescription() {
        return fullDescription;
    }

    public String getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }
}