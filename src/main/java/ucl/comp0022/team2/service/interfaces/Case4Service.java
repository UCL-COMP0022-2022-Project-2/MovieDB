package ucl.comp0022.team2.service.interfaces;

public interface Case4Service {
    /**
     * get the movie predicted score by id
     * @param movieId the id of the movie
     * @return the predicted score of the movie
     */
    double getPredictedScoreByMovieId(int movieId);
}
