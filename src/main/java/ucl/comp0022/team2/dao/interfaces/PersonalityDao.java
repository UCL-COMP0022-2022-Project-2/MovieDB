package ucl.comp0022.team2.dao.interfaces;

import java.util.HashMap;

public interface PersonalityDao {
    void initGenrePersonality();
    double[] getMoviePersonality(int movieIdParam);
}
