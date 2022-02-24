package ucl.comp0022.team2.dao.impl;

import org.springframework.stereotype.Repository;
import ucl.comp0022.team2.helper.MySQLHelper;
import ucl.comp0022.team2.dao.interfaces.MovieInfoDao;
import ucl.comp0022.team2.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
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
     *                   2: selecting by {@code Avg Rating}
     *                   3: selecting by {@code Genre}
     *                   4: selecting by {@code Year}
     *                   N.B. 0 is only permitted to emerge at head of the list
     * @param selectValue Selection parameters in terms of a list of String
     * @param sortEnum 0: no sorting
     *                 1: sorting by {@code Title}
     *                 2: sorting by {@code Avg Rating}
     *                 3: sorting by {@code Year}
     *                 N.B. 0 is only permitted to emerge at head of the list
     * @param sortBoolean Sorting parameters in terms of a list of Boolean
     *                    TRUE: sorting from the SMALLEST to the LARGEST
     *                    FALSE: sorting from the LARGEST to the SMALLEST
     * @param limitValue limitation parameters in terms of a String
     *                   -1: no limitation
     *                   single integer (e.g., 10): read 10 lines from the first line (incl. the first line)
     *                   double integers (e.g., 10, 10): read 10 lines from the 11th line (incl. the 11th line)
     * @return movieList
     */
    @Override
    public List<Movie> getSelectedAndSortedMovieList(List<Integer> selectEnum, List<String> selectValue, List<Integer> sortEnum, List<Boolean> sortBoolean, String limitValue) {
        List<Movie> movieList = new ArrayList<>();
        try {
            Connection conn = MySQLHelper.getConnection();

            StringBuilder sql = new StringBuilder(
                    "SELECT m.*, AVG(r.rating) AS avg FROM movies AS m INNER JOIN ratings AS r ON m.movieId = r.movieId" +
                    " GROUP BY movieId, title, genres, year");
            List<String> param = new ArrayList<>();

            if(selectEnum.get(0) != 0 || sortEnum.get(0) != 0) {
                for(int i = 0; i < selectEnum.size(); i++) {
                    if(i == 0) {
                        if(selectEnum.get(0) == 0) {
                            break;
                        } else {
                            if(selectEnum.get(0) == 1) {
                                sql.append(" HAVING title LIKE ?");
                                param.add("%" + selectValue.get(0) + "%");
                            } else if(selectEnum.get(0) == 2) {
                                if(selectValue.get(0).contains("-")) {
                                    String[] ratings = selectValue.get(0).split("-");
                                    if(ratings.length == 1) {
                                        if(selectValue.get(0).startsWith("-")) {
                                            sql.append(" HAVING avg <= ?");
                                        } else {
                                            sql.append(" HAVING avg >= ?");
                                        }
                                        param.add(ratings[0].trim());
                                    } else if(ratings.length == 2) {
                                        sql.append(" HAVING avg >= ? AND avg <= ?");
                                        param.add(ratings[0].trim());
                                        param.add(ratings[1].trim());
                                    }
                                } else {
                                    sql.append(" HAVING avg = ?");
                                    param.add(selectValue.get(0));
                                }
                            } else if(selectEnum.get(0) == 3) {
                                sql.append(" HAVING genres LIKE ?");
                                param.add("%" + selectValue.get(0) + "%");
                            } else if(selectEnum.get(0) == 4) {
                                if(selectValue.get(0).contains("-")) {
                                    String[] years = selectValue.get(0).split("-");
                                    if(years.length == 1) {
                                        if(selectValue.get(0).startsWith("-")) {
                                            sql.append(" HAVING year != 0 AND year <= ?");
                                        } else {
                                            sql.append(" HAVING year != 0 AND year >= ?");
                                        }
                                        param.add(years[0].trim());
                                    } else if(years.length == 2) {
                                        sql.append(" HAVING year != 0 AND year >= ? AND year <= ?");
                                        param.add(years[0].trim());
                                        param.add(years[1].trim());
                                    }
                                } else {
                                    sql.append(" HAVING year != 0 AND year = ?");
                                    param.add(selectValue.get(0));
                                }
                            }
                        }
                    } else {
                        if(selectEnum.get(i) == 1) {
                            sql.append(" AND title LIKE ?");
                            param.add("%" + selectValue.get(i) + "%");
                        } else if(selectEnum.get(i) == 2) {
                            if(selectValue.get(i).contains("-")) {
                                String[] ratings = selectValue.get(i).split("-");
                                if(ratings.length == 1) {
                                    if(selectValue.get(i).startsWith("-")) {
                                        sql.append(" AND avg <= ?");
                                    } else {
                                        sql.append(" AND avg >= ?");
                                    }
                                    param.add(ratings[0].trim());
                                } else if(ratings.length == 2) {
                                    sql.append(" AND avg >= ? AND avg <= ?");
                                    param.add(ratings[0].trim());
                                    param.add(ratings[1].trim());
                                }
                            } else {
                                sql.append(" AND avg = ?");
                                param.add(selectValue.get(i));
                            }
                        } else if(selectEnum.get(i) == 3) {
                            sql.append(" AND genres LIKE ?");
                            param.add("%" + selectValue.get(i) + "%");
                        } else if(selectEnum.get(i) == 4) {
                            if(selectValue.get(i).contains("-")) {
                                String[] years = selectValue.get(i).split("-");
                                if(years.length == 1) {
                                    if(selectValue.get(i).startsWith("-")) {
                                        sql.append(" AND year != 0 AND year <= ?");
                                    } else {
                                        sql.append(" AND year != 0 AND year >= ?");
                                    }
                                    param.add(years[0].trim());
                                } else if(years.length == 2) {
                                    sql.append(" AND year != 0 AND year >= ? AND year <= ?");
                                    param.add(years[0].trim());
                                    param.add(years[1].trim());
                                }
                            } else {
                                sql.append(" AND year != 0 AND year = ?");
                                param.add(selectValue.get(i));
                            }
                        }
                    }
                }
                for(int i = 0; i < sortEnum.size(); i++) {
                    if(i == 0) {
                        if(sortEnum.get(0) == 0) {
                            sql.append(" ORDER BY movieId");
                            break;
                        } else {
                            if(sortEnum.get(0) == 1) {
                                sql.append(" ORDER BY title");
                            } else if(sortEnum.get(0) == 2) {
                                sql.append(" ORDER BY avg");
                            } else if(sortEnum.get(0) == 3) {
                                sql.append(" ORDER BY year");
                            }
                            if(!sortBoolean.get(0)) {
                                sql.append(" DESC");
                            }
                        }
                    } else {
                        if(sortEnum.get(i) == 1) {
                            sql.append(", title");
                        } else if(sortEnum.get(i) == 2) {
                            sql.append(", avg");
                        } else if(sortEnum.get(i) == 3) {
                            sql.append(", year");
                        }
                        if(!sortBoolean.get(i)) {
                            sql.append(" DESC");
                        }
                    }
                    if(i == sortEnum.size() - 1) {
                        sql.append(", movieId");
                    }
                }
            } else {
                sql.append(" ORDER BY movieId");
            }

            if(!limitValue.equals("-1")) {
                String[] limitations = limitValue.split(",");
                if(limitations.length == 1) {
                    sql.append(" LIMIT ").append(limitations[0].trim());
                } else if(limitations.length == 2) {
                    sql.append(" LIMIT ").append(limitations[0].trim()).
                            append(", ").append(limitations[1].trim());
                }
            }

            ResultSet rs = MySQLHelper.executingQuery(conn, sql.append(";").toString(), param);

            while(rs.next()) {
                Movie movie = new Movie();
                int movieId = rs.getInt("movieId");
                String title = rs.getString("title");
                double avg = rs.getDouble("avg");
                String genres = rs.getString("genres");
                int year = rs.getInt("year");

                movie.setMovieId(movieId);
                movie.setTitle(title);
                movie.setRating(Double.parseDouble(new DecimalFormat("######0.0").format(avg)));
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
        System.out.println(new MovieInfoDaoImpl().getSelectedAndSortedMovieList(
                new ArrayList<>(Arrays.asList(2, 4)),
                new ArrayList<>(Arrays.asList("3-5", "1995")),
                new ArrayList<>(Arrays.asList(2, 1)),
                new ArrayList<>(Arrays.asList(false, true)),
                "10, 10"));
    }
}
