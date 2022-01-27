package ucl.comp0022.team2.dao.interfaces;

import ucl.comp0022.team2.model.Movie;

import java.util.List;

public interface MovieSelectedDao {
    List<Movie> getSelectedMovieListByTitle(List<Movie> movieList, String title);
    List<Movie> getSelectedMovieListByRating(List<Movie> movieList, int rating1, int rating2);
    List<Movie> getSelectedMovieListByGenre(List<Movie> movieList, String genre);
    List<Movie> getSelectedMovieListByYear(List<Movie> movieList, int year1, int year2);
}
