package cs2340.group61.doughnation.model.domain;

public class Donation {
    public String id;
    public String title;
    public String timestamp;
    public String location;
    public String shortdescription;
    public String fulldescription;
    public String value;
    public String category;

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
        this.shortdescription = shortDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fulldescription = fullDescription;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}