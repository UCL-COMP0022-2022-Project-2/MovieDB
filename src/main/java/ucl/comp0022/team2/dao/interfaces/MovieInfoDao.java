package ucl.comp0022.team2.dao.interfaces;

import ucl.comp0022.team2.model.Movie;

public interface MovieInfoDao {
    Movie getMovieInfoByMovieId(int movieId);
    Movie getMovieInfoByTitle(String title);
    Movie getMovieInfoByRating(double rating);
    Movie getMovieInfoByGenre(String genre);
    Movie getMovieInfoByYear(int year);
}
