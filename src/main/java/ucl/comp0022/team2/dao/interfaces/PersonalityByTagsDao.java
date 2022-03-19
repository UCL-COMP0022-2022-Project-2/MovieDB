package ucl.comp0022.team2.dao.interfaces;

import ucl.comp0022.team2.model.Personality;

import java.util.HashMap;
import java.util.List;

public interface PersonalityByTagsDao {
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
    HashMap<String, Personality> getPersonalitiesByTags(List<String> list);

    /**
     * get the tags by initial letter, # for numbers and special characters
     * @param capital the initial letter
     * @return a list of tags
     */
    List<String> getTagsByInitialLetter(char capital);

}
