package ucl.comp0022.team2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucl.comp0022.team2.dao.interfaces.PredictionDao;
import ucl.comp0022.team2.service.interfaces.Case4Service;
@Service
public class Case4ServiceImpl implements Case4Service {

    private PredictionDao predictionDao;

    @Override
    public double getPredictedScoreByMovieId(int movieId) {
        return predictionDao.getPredictionScoreById(movieId);
    }

    @Autowired
    public void setPredictionDao(PredictionDao predictionDao) {
        this.predictionDao = predictionDao;
    }

}
