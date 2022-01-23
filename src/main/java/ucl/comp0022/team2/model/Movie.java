package ucl.comp0022.team2.model;

import java.util.Arrays;

public class Movie {
    private int movieId;
    private String title;
    private String[] genresList;
    private int year;

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setGenres(String genres) {
        genresList = genres.split("\\|");
    }

    public String[] getGenres() {
        return genresList;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title=" + title +
                ", genres=" + Arrays.toString(genresList) +
                ", year=" + year +
                "}";
    }
}
