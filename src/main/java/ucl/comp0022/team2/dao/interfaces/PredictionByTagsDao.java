package ucl.comp0022.team2.dao.interfaces;

import ucl.comp0022.team2.model.Personality;

import java.util.HashMap;

public interface PredictionByTagsDao {
    /**
     * Insert relevant values. Invoke if new data have been inserted.
     * @return whether initiated successfully
     */
    boolean initialize();

    /**
     * get the personality by tags
     * @param tags the tags of a movie
     * @return the HashMap, key being the tag and value being the personality
     */
    HashMap<String, Personality> getPersonalitiesByTags(String[] tags);

}
