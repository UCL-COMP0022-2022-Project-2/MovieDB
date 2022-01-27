package ucl.comp0022.team2.model;

public class Report {
    private int userId;
    private double rating;
    private String tags;
    private String timestamp;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getRatings() {
        return rating;
    }

    public void setRating(double ratings) {
        this.rating = ratings;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Report{" +
                "userId=" + userId +
                ", rating=" + rating +
                ", tags='" + tags + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
