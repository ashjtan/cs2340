package cs2340.group61.doughnation.model.domain;

public class Donation {
    private String id;
    private String title;
    private String timestamp;
    private String location;
    private String shortdescription;
    private String fulldescription;
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

    public void setShortdescription(String shortDescription) {
        this.shortdescription = shortDescription;
    }

    public void setFulldescription(String fullDescription) {
        this.fulldescription = fullDescription;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLocation() {
        return location;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public String getFulldescription() {
        return fulldescription;
    }

    public String getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }
}