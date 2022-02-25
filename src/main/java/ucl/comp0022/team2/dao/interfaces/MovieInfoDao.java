package ucl.comp0022.team2.dao.interfaces;

import ucl.comp0022.team2.model.Movie;

import java.util.List;

public interface MovieInfoDao {
    Movie getMovieInfoByMovieId(int movieId);
    List<Movie> getSelectedAndSortedMovieList(List<Integer> selectEnum, List<String> selectValue, List<Integer> sortEnum, List<Boolean> sortBoolean, String limitValue);
    Integer getMovieCount();
}
