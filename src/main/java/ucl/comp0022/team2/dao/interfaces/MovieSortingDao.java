package ucl.comp0022.team2.dao.interfaces;

import ucl.comp0022.team2.model.Movie;

import java.util.List;

public interface MovieSortingDao {
    List<Movie> getSortedMovieListByTitle(List<Movie> movieList);
    List<Movie> getSortedMovieListByRatings(List<Movie> movieList);
    List<Movie> getSortedMovieListByYear(List<Movie> movieList);
}
