package ucl.comp0022.team2.dao.interfaces;

import ucl.comp0022.team2.model.Movie;

import java.util.List;

public interface MovieInfoDao {
    Movie getMovieInfoByMovieId(int movieId);
    List<Movie> getMovieInfoByTitle(String title);
    List<Movie> getMovieInfoByGenre(String genre);
    List<Movie> getMovieInfoByYear(int year);
}
