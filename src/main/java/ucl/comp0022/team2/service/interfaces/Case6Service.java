package ucl.comp0022.team2.service.interfaces;

import ucl.comp0022.team2.model.Personality;

import java.util.HashMap;
import java.util.List;

public interface Case6Service {
    /**
     * get the tags by first letter
     * @param letter the first letter of tag
     * @return a list of tags, starting with that letter
     */
    List<String> getTagsByFirstLetter(char letter);

    /**
     * get the personalities by tags
     * @param tags the tags
     * @return the HashMap, tag being the key and personality being the value
     */
    HashMap<String, Personality> getPersonalitiesByTags(String[] tags);

}
