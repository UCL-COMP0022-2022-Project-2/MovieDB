package ucl.comp0022.team2.service.interfaces;

import ucl.comp0022.team2.model.Movie;

import java.util.List;

public interface Case1Service {

    /**
     * The method that gets all movies
     * @return all the movies
     */
    List<Movie> getAllMovies();

    /**
     * The method that get movies required by users
     * Please always provide two String lists, the first one of size 4, the second one of size 2.
     * Please fill "" (empty string) if the user has not specified that parameter.
     *
     * @param selectParams [titleParam, ratingsParam, genreParam, yearParam]
     *                     titleParam:      the input string in the searchBar by the user
     *                     ratingParam:     the ratings string.
     *                                      For example, "-4" represents user wants rating from 0 to 4
     *                                      "1-3" represents user wants rating from 1 to 3
     *                     genreParam:      the genre string selected by the user
     *                                      see database for all genres
     *                     yearParam:       the year string selected by the user
     * @param sortParams [sortParam, order, limit]
     *                      sortParam:      the sort method required by the user
     *                                      "title" for order by title
     *                                      "rating" for order by rating
     *                                      "year" for order by time
     *                      order:          "asc" for ascending order
     *                                      "desc" for descending order
     *                      limit:          "[startIndex],[range]"
     *                                      For example
     *                                      "0,10": read items 1-10
     *                                      "10,10": read items 11 - 20
     *                                      "10,30": read items 11 - 40
     * @return the movies required by users
     */
    List<Movie> getMovies(String[] selectParams, String[] sortParams);

    /**
     * The method that gets the total numebr of movies
     * @return the number of movies
     */
    Integer getMoviesCount();

}
