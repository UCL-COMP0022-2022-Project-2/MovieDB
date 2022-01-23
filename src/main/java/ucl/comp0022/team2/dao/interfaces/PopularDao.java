package ucl.comp0022.team2.dao.interfaces;

import java.util.HashMap;
import java.util.List;

public interface PopularDao {
    List<HashMap.Entry<String, Double>> getRanking();
}
