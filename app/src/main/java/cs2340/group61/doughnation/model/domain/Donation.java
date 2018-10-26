package cs2340.group61.doughnation.model.domain;

public class Donation {
    public String title;
    public String timestamp;
    public String location;
    public String shortDescription;
    public String fullDescription;
    public String value;
    public String category;
    public String comments;
    public String organization;

    public Donation() {}

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

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
