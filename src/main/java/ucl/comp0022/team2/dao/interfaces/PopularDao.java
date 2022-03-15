package ucl.comp0022.team2.dao.interfaces;

import java.util.HashMap;
import java.util.List;
import ucl.comp0022.team2.model.Movie;

public interface PopularDao {
    List<Movie> getPopularMovieAvgList(String limit);
    List<Movie> getPopularMovieTagsList(String limit);
    List<Movie> getPopularMovieRatingsList(String limit);
}