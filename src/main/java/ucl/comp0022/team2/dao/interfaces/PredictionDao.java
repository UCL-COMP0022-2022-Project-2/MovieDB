package ucl.comp0022.team2.dao.interfaces;

import java.util.HashMap;
import java.util.List;

public interface PredictionDao {
    List<List<Object>> getForecastMovieList();

    /**
     * insert the values in the database. Invoke at anytime if database updates.
     * @return whether successfully initialized
     */
    boolean initialize();

    /**
     * get the predicted score giving the movieId
     * @param movieId the id of the movie
     * @return the predicted score
     */
    double getPredictionScoreById(int movieId);

}
