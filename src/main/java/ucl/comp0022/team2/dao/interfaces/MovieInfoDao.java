package ucl.comp0022.team2.dao.interfaces;

import ucl.comp0022.team2.model.Movie;

import java.util.List;

public interface MovieInfoDao {
    Movie getMovieInfoByMovieId(int movieId);
    List<Movie> getSelectedAndSortedMovieList(int selectEnum, String selectValue, int sortEnum, boolean sortBoolean);
}
