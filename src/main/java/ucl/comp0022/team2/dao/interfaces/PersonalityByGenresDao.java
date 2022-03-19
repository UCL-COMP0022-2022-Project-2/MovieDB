package ucl.comp0022.team2.dao.interfaces;

import ucl.comp0022.team2.model.Personality;

import java.util.HashMap;
import java.util.List;

public interface PersonalityByGenresDao {
    void initGenrePersonality();
    HashMap<String, Personality> getMoviePersonality(List<String> tags);
}
