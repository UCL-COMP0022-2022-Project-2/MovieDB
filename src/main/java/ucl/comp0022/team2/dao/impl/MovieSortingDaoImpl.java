package ucl.comp0022.team2.dao.impl;

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
        movieList.sort((o1, o2) -> (int) (o2.getRating() - o1.getRating()));
        return movieList;
    }

    @Override
    public List<Movie> getSortedMovieListByYear(List<Movie> movieList) {
        movieList.sort((o1, o2) -> o2.getYear() - o1.getYear());
        return movieList;
    }

    public static void main(String[] args) {
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = new Movie();
        movie1.setMovieId(1);
        movie1.setTitle("Toy Story");
        movie1.setGenres("Adventure|Animation|Children|Comedy|Fantasy");
        movie1.setRating(5.0);
        movie1.setYear(1995);
        movieList.add(movie1);

        Movie movie2 = new Movie();
        movie2.setMovieId(2);
        movie2.setTitle("Jumanji");
        movie2.setGenres("Adventure|Children|Fantasy");
        movie2.setRating(2.0);
        movie2.setYear(1991);
        movieList.add(movie2);

        System.out.println(new MovieSortingDaoImpl().getSortedMovieListByTitle(movieList));
        System.out.println(new MovieSortingDaoImpl().getSortedMovieListByRatings(movieList));
        System.out.println(new MovieSortingDaoImpl().getSortedMovieListByYear(movieList));

    }
}