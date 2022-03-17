package ucl.comp0022.team2.dao.interfaces;

import java.util.HashMap;
import java.util.List;
import ucl.comp0022.team2.model.Movie;

public interface PopularDao {
    //order by ratings
    List<Movie> getPopularMovieAvgList(String limit);
    //order by number of tags
    List<Movie> getPopularMovieTagsList(String limit);
    //order by number of ratings
    List<Movie> getPopularMovieRatingsList(String limit);
}