package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.dao.interfaces.PredictionByTagsDao;
import ucl.comp0022.team2.model.Personality;

import java.util.HashMap;

public class PredictionByTagsImpl implements PredictionByTagsDao {
    @Override
    public boolean initialize() {
        return false;
    }

    @Override
    public HashMap<String, Personality> getPersonalitiesByTags(String[] tags) {
        return null;
    }
}
