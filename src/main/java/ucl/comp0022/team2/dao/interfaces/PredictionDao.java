package ucl.comp0022.team2.dao.interfaces;

import java.util.HashMap;
import java.util.List;

public interface PredictionDao {
    List<HashMap<Integer, Double>> getForecastMovieList();
}
