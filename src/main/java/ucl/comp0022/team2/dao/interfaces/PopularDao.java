package ucl.comp0022.team2.dao.interfaces;

import java.util.HashMap;
import java.util.List;
import ucl.comp0022.team2.model.Movie;

public interface PopularDao {
    List<Movie> getPopularMovieAvgList();
    List<HashMap<Integer, Double>> getPopularMovieTagsList();
    List<HashMap<Integer, Double>> getPopularMovieRatingsList();
}