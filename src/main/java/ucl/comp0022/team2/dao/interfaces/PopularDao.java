package ucl.comp0022.team2.dao.interfaces;

import java.util.HashMap;
import java.util.List;

public interface PopularDao {
    /**
     * avg
     * @return
     */
    List<HashMap<Integer, Double>> getPopularMovieAvgList();
    /**
     * Tags数量
     * @return
     */
    List<HashMap<Integer, Double>> getPopularMovieTagsList();
    /**
     * Ratings数量
     * @return
     */
    List<HashMap<Integer, Double>> getPopularMovieRatingsList();
}