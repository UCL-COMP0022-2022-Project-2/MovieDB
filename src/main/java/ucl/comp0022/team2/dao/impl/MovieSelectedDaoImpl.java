package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.dao.interfaces.MovieInfoDao;
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
            if (movie.getRatings() >= rating1 && movie.getRatings() <= rating2) {
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
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = new Movie();
        movie1.setMovieId(1);
        movie1.setTitle("Toy Story");
        movie1.setGenres("Adventure|Animation|Children|Comedy|Fantasy");
        movie1.setRatings(5.0);
        movie1.setYear(1995);
        movieList.add(movie1);

        Movie movie2 = new Movie();
        movie2.setMovieId(2);
        movie2.setTitle("Jumanji");
        movie2.setGenres("Adventure|Children|Fantasy");
        movie2.setRatings(2.0);
        movie2.setYear(1991);
        movieList.add(movie2);

        System.out.println(new MovieSelectedDaoImpl().getSelectedMovieListByTitle(movieList, "Toy Story"));
        System.out.println(new MovieSelectedDaoImpl().getSelectedMovieListByRating(movieList, 0, 2));
        System.out.println(new MovieSelectedDaoImpl().getSelectedMovieListByGenre(movieList, "Animation"));
        System.out.println(new MovieSelectedDaoImpl().getSelectedMovieListByYear(movieList, 1900, 1992));

    }
}
