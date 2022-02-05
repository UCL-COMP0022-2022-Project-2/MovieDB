package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.dao.interfaces.MovieInfoDao;
import ucl.comp0022.team2.dao.interfaces.MovieSortingDao;
import ucl.comp0022.team2.model.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MovieSortingDaoImpl implements MovieSortingDao {
    @Override
    public List<Movie> getSortedMovieListByTitle(List<Movie> movieList) {
        movieList.sort((o1, o2) -> o2.getTitle().compareTo(o1.getTitle()));
        return movieList;
    }

    @Override
    public List<Movie> getSortedMovieListByRatings(List<Movie> movieList) {
        movieList.sort((o1, o2) -> Double.compare(o2.getRating(), o1.getRating()));
        return movieList;
    }

    @Override
    public List<Movie> getSortedMovieListByYear(List<Movie> movieList) {
        movieList.sort((o1, o2) -> o2.getYear() - o1.getYear());
        return movieList;
    }

    public static void main(String[] args) {
        List<Movie> movieList = new MovieInfoDaoImpl().getMovieInfoByGenre("Film-Noir");

        System.out.println(new MovieSortingDaoImpl().getSortedMovieListByTitle(movieList));
        System.out.println(new MovieSortingDaoImpl().getSortedMovieListByRatings(movieList));
        System.out.println(new MovieSortingDaoImpl().getSortedMovieListByYear(movieList));
    }
}
