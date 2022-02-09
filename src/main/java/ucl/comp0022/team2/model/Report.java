package ucl.comp0022.team2.model;

import java.util.Date;

public class Report {
    private int userId;
    private double rating;
    private String tags;
    private Date ratingTimestamp;
    private Date tagTimestamp;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getRating() {
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

    public Date getRatingTimestamp() {
        return ratingTimestamp;
    }

    public void setRatingTimestamp(Date timestamp) {
        this.ratingTimestamp = timestamp;
    }

    public Date getTagTimestamp() {
        return tagTimestamp;
    }

    public void setTagTimestamp(Date timestamp) {
        this.tagTimestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Report{" +
                "userId=" + userId +
                ", rating=" + rating +
                ", ratingTimestamp=" + ratingTimestamp +
                ", tags='" + tags + '\'' +
                ", tagTimestamp=" + tagTimestamp +
                "}\n";
    }
}
