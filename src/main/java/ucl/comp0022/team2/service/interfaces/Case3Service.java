package ucl.comp0022.team2.service.interfaces;

import ucl.comp0022.team2.model.Movie;

import java.util.List;

public interface Case3Service {
    /**
     *
     * @param limit the limit in sql
     * @return a list ordered by polarization
     */
    List<Movie> getPolarizingMovies(String limit);
    /**
     *
     * @param limit the limit in sql
     * @return a list ordered by popularity, using an average of number of tags and ratings
     */
    List<Movie> getPopularMoviesAverage(String limit);
    /**
     *
     * @param limit the limit in sql
     * @return a list ordered by popularity, using number of tags
     */
    List<Movie> getPopularMoviesByTags(String limit);
    /**
     *
     * @param limit the limit in sql
     * @return a list ordered by popularity, using ratings
     */
    List<Movie> getPopularMoviesByRating(String limit);
}
