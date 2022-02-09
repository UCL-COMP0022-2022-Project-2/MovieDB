package ucl.comp0022.team2.model;

import java.util.Arrays;

public class Movie {
    private int movieId;
    private String title;
    private double rating;
    private String[] genres;
    private int year;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double avgRating) {
        this.rating = avgRating;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres.split("\\|");
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", genres=" + Arrays.toString(genres) +
                ", year=" + year +
                "}\n";
    }
}
