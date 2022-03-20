package ucl.comp0022.team2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucl.comp0022.team2.dao.interfaces.PersonalityByGenresDao;
import ucl.comp0022.team2.model.Personality;
import ucl.comp0022.team2.service.interfaces.Case5Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class Case5ServiceImpl implements Case5Service {

    private PersonalityByGenresDao personalityByGenresDao;
    private boolean isInitialized = false;

    @Override
    public void initialize() {
        if(!isInitialized){
            personalityByGenresDao.initialize();
            isInitialized = true;
        }
    }

    @Override
    public List<String> getAllGenres() {
        return personalityByGenresDao.getAllGenres();
    }

    @Override
    public HashMap<String, Personality> getPersonalitiesByGenres(String[] genres) {
        return personalityByGenresDao.getMoviePersonality(Arrays.asList(genres));
    }

    @Override
    public Personality getAllGenresAveragePersonality() {
        return personalityByGenresDao.getAllGenresAveragePersonality();
    }

    @Autowired
    public void setPersonalityByGenresDao(PersonalityByGenresDao personalityByGenresDao) {
        this.personalityByGenresDao = personalityByGenresDao;
    }
}
