package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.helper.MySQLHelper;
import ucl.comp0022.team2.dao.interfaces.MovieInfoDao;
import ucl.comp0022.team2.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieInfoDaoImpl implements MovieInfoDao {

    @Override
    public Movie getMovieInfoByMovieId(int movieIdParam) {

        Movie movie = new Movie();
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "SELECT * FROM movies WHERE movieId = ?;";
            List<Integer> param = new ArrayList<>();
            param.add(movieIdParam);

            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, param);

            // Reading, analysing and saving the results...
            while(rs.next()) {
                int movieId = rs.getInt("movieId");
                String title = rs.getString("title");
                String genres = rs.getString("genres");
                int year = rs.getInt("year");

                movie.setMovieId(movieId);
                movie.setTitle(title);
                if(!genres.equals("NULL")) movie.setGenres(genres);
                movie.setYear(year);
            }

            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movie;
    }

    /**
     * An integrated method to select and sort movies.
     * @param selectEnum 0: no selection
     *                   1: selecting by {@code Title}
     *                   2: selecting by {@code Rating}
     *                   3: selecting by {@code Genre}
     *                   4: selecting by {@code Year}
     * @param selectValue Selection parameter in terms of String
     * @param sortEnum 0: no sorting
     *                 1: sorting by {@code Title}
     *                 2: sorting by {@code Rating}
     *                 3: sorting by {@code Year}
     * @param sortBoolean TRUE: sorting from the SMALLEST to the LARGEST
     *                    FALSE: sorting from the LARGEST to the SMALLEST
     * @return movieList
     */
    @Override
    public List<Movie> getSelectedAndSortedMovieList(int selectEnum, String selectValue, int sortEnum, boolean sortBoolean) {
        List<Movie> movieList = new ArrayList<>();
        try {
            Connection conn = MySQLHelper.getConnection();

            String sql = "SELECT * FROM movies";
            List<String> param = new ArrayList<>();

            if(selectEnum != 0 || sortEnum != 0) {
                if(selectEnum == 1) {
                    sql += " WHERE title LIKE ?";
                    param.add("%" + selectValue + "%");
                } else if(selectEnum == 2) {
                    if(selectValue.contains("-")) {
                        String[] ratings = selectValue.split("-");
                        if(ratings.length == 1) {
                            if(selectValue.startsWith("-")) {
                                sql += " WHERE (SELECT AVG(rating) FROM ratings WHERE moviedb.ratings.movieId = moviedb.movies.movieId) <= ?";
                            } else {
                                sql += " WHERE (SELECT AVG(rating) FROM ratings WHERE moviedb.ratings.movieId = moviedb.movies.movieId) >= ?";
                            }
                            param.add(ratings[0]);
                        } else if(ratings.length == 2) {
                            sql += " WHERE (SELECT AVG(rating) FROM ratings WHERE moviedb.ratings.movieId = moviedb.movies.movieId) >= ? " +
                                    "AND (SELECT AVG(rating) FROM ratings WHERE moviedb.ratings.movieId = moviedb.movies.movieId) <= ?";
                            param.add(ratings[0]);
                            param.add(ratings[1]);
                        }
                    } else {
                        sql += " WHERE (SELECT AVG(rating) FROM ratings WHERE moviedb.ratings.movieId = moviedb.movies.movieId) = ?";
                        param.add(selectValue);
                    }
                } else if(selectEnum == 3) {
                    sql += " WHERE genres LIKE ?";
                    param.add("%" + selectValue + "%");
                } else if(selectEnum == 4) {
                    if(selectValue.contains("-")) {
                        String[] years = selectValue.split("-");
                        if(years.length == 1) {
                            if(selectValue.startsWith("-")) {
                                sql += " WHERE year != 0 AND year <= ?";
                            } else {
                                sql += " WHERE year != 0 AND year >= ?";
                            }
                            param.add(years[0]);
                        } else if(years.length == 2) {
                            sql += " WHERE year != 0 AND year >= ? AND year <= ?";
                            param.add(years[0]);
                            param.add(years[1]);
                        }
                    } else {
                        sql += " WHERE year != 0 AND year = ?";
                        param.add(selectValue);
                    }
                }
                if(sortEnum == 1) {
                    sql += " ORDER BY title";
                    if(!sortBoolean) {
                        sql += " DESC";
                    }
                } else if(sortEnum == 2) {
                    sql += " ORDER BY (SELECT AVG(rating) FROM ratings WHERE moviedb.ratings.movieId = moviedb.movies.movieId)";
                    if(!sortBoolean) {
                        sql += " DESC";
                    }
                } else if(sortEnum == 3) {
                    sql += " ORDER BY year";
                    if(!sortBoolean) {
                        sql += " DESC";
                    }
                }

            }

            ResultSet rs = MySQLHelper.executingQuery(conn, sql + ";", param);

            while(rs.next()) {
                Movie movie = new Movie();
                int movieId = rs.getInt("movieId");
                String title = rs.getString("title");
                String genres = rs.getString("genres");
                int year = rs.getInt("year");

                movie.setMovieId(movieId);
                movie.setTitle(title);
                if(!genres.equals("NULL")) movie.setGenres(genres);
                movie.setYear(year);
                movieList.add(movie);
            }

            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movieList;
    }

    public static void main(String[] args) {
        // System.out.println(new MovieInfoDaoImpl().getMovieInfoByMovieId(1));
        // System.out.println(new MovieInfoDaoImpl().getMovieInfoByTitle("Sabrina"));
        // System.out.println(new MovieInfoDaoImpl().getMovieInfoByGenre("Film-Noir"));
        // System.out.println(new MovieInfoDaoImpl().getMovieInfoByYear(1920));
        System.out.println(new MovieInfoDaoImpl().getSelectedAndSortedMovieList(2, "5-", 1, false));
    }
}
