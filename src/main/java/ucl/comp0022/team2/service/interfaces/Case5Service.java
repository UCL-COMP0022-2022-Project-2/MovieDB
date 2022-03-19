package ucl.comp0022.team2.service.interfaces;

import ucl.comp0022.team2.model.Personality;

import java.util.HashMap;
import java.util.List;

public interface Case5Service {
    /**
     * initialize the database
     */
    void initialize();

    /**
     * get all the genres
     */
    List<String> getAllGenres();

    /**
     * get the personalities by genres
     * @param genres the genres
     * @return the HashMap, genre being the key and personality being the value
     */
    HashMap<String, Personality> getPersonalitiesByGenres(String[] genres);

    /**
     * get the average personality of all genres
     * @return the average personality of all genres
     */
    Personality getAllGenresAveragePersonality();
}
