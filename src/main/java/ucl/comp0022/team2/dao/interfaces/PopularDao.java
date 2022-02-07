package ucl.comp0022.team2.dao.interfaces;

import java.util.HashMap;
import java.util.List;

public interface PopularDao {
    List<HashMap<Integer, Double>> getPopularMovieAvgList();
    List<HashMap<Integer, Double>> getPopularMovieTagsList();
    List<HashMap<Integer, Double>> getPopularMovieRatingsList();
}