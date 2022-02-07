package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.dao.interfaces.MovieSelectedDao;
import ucl.comp0022.team2.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSelectedDaoImpl implements MovieSelectedDao {
    @Override
    public List<Movie> getSelectedMovieListByTitle(List<Movie> movieList, String title) {
        List<Movie> movieList1 = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getTitle().equals(title)) {
                movieList1.add(movie);
            }
        }
        return movieList1;
    }

    @Override
    public List<Movie> getSelectedMovieListByRating(List<Movie> movieList, int rating1, int rating2) {
        List<Movie> movieList1 = new ArrayList<>();
        for (Movie movie : movieList) {
            if (SearchingReportDaoImpl.getAverageScore(movie.getMovieId()) >= rating1
                    && SearchingReportDaoImpl.getAverageScore(movie.getMovieId()) <= rating2) {
                movieList1.add(movie);
            }
        }
        return movieList1;
    }

    @Override
    public List<Movie> getSelectedMovieListByGenre(List<Movie> movieList, String genre) {
        List<Movie> movieList1 = new ArrayList<>();
        for (Movie movie : movieList) {
            for (String gen : movie.getGenres()) {
                if (gen.equals(genre)) {
                    movieList1.add(movie);
                }
            }
        }
        return movieList1;
    }

    @Override
    public List<Movie> getSelectedMovieListByYear(List<Movie> movieList, int year1, int year2) {
        List<Movie> movieList1 = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getYear() >= year1 && movie.getYear() <= year2) {
                movieList1.add(movie);
            }
        }
        return movieList1;
    }

    public static void main(String[] args) {
        List<Movie> movieList = new MovieInfoDaoImpl().getMovieInfoByGenre("Film-Noir");

        System.out.println(new MovieSelectedDaoImpl().getSelectedMovieListByTitle(movieList, "You Only Live Once"));
        System.out.println(new MovieSelectedDaoImpl().getSelectedMovieListByRating(movieList, 4, 5));
        System.out.println(new MovieSelectedDaoImpl().getSelectedMovieListByGenre(movieList, "Crime"));
        System.out.println(new MovieSelectedDaoImpl().getSelectedMovieListByYear(movieList, 1930, 1940));
    }
}
