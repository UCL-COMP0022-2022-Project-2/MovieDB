package ucl.comp0022.team2.dao.interfaces;

import java.util.HashMap;
import java.util.List;

public interface PolarisingDao {
    /**
     * 平均分
     * @return
     */
    List<HashMap<Integer, Double>> getPolarisingMovieList();
}