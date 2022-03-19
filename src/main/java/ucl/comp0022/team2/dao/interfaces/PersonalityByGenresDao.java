package ucl.comp0022.team2.dao.interfaces;

import ucl.comp0022.team2.model.Personality;

import java.util.HashMap;
import java.util.List;

public interface PersonalityByGenresDao {
    /**
     * Insert relevant values. Invoke if new data have been inserted.
     */
    void initialize();

    /**
     * get genres personalities
     * @param genres genres
     * @return the personalities of genres
     */
    HashMap<String, Personality> getMoviePersonality(List<String> genres);

    /**
     * get the average personality of all genres
     * @return the personality
     */
    Personality getAllGenresAveragePersonality();

    /**
     * get all genres
     * @return all genres
     */
    List<String> getAllGenres();
}
