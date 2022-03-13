package ucl.comp0022.team2.dao.interfaces;

import java.util.HashMap;

public interface PersonalityDao {
    HashMap<String, double[]> initGenrePersonality(HashMap<String, double[]> genreMap);
    double[] getMoviePersonality(int movieIdParam,HashMap<String, double[]> genreMap);
}
