package ucl.comp0022.team2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucl.comp0022.team2.dao.interfaces.MovieInfoDao;
import ucl.comp0022.team2.model.Movie;
import ucl.comp0022.team2.service.interfaces.Case1Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Case1ServiceImpl implements Case1Service {

    private MovieInfoDao movieInfoDao;

    /**
     * @inheritDoc
     */
    @Override
    public List<Movie> getAllMovies() {
        ArrayList<Integer> selectEnum = new ArrayList<>();
        ArrayList<Integer> sortEnum = new ArrayList<>();
        selectEnum.add(0);
        sortEnum.add(0);
        return movieInfoDao.getSelectedAndSortedMovieList(selectEnum, null, sortEnum, null);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Movie> getMovies(String[] selectParams, String[] sortParams) {
        ArrayList<Integer> selectEnum = new ArrayList<>();
        ArrayList<String> selectValue = new ArrayList<>();
        ArrayList<Integer> sortEnum = new ArrayList<>();
        ArrayList<Boolean> sortBoolean = new ArrayList<>();

        if(selectParams[0].equals("") && selectParams[1].equals("") && selectParams[2].equals("") && selectParams[3].equals("")){
            selectEnum.add(0);
        } else {
            for(int i = 0; i < selectParams.length; i++) {
                if(!selectParams[i].equals("")){
                    selectEnum.add(i+1);
                    selectValue.add(selectParams[i]);
                }
            }
        }
        if(!sortParams[0].equals("")){
            switch (sortParams[0]){
                case "title":
                    sortEnum.add(1);
                    break;
                case "rating":
                    sortEnum.add(2);
                    break;
                case "year":
                    sortEnum.add(3);
                    break;
            }
        } else {
            sortEnum.add(0);
        }
        if(sortParams[1].equals("desc")){
            sortBoolean.add(false);
        } else {
            sortBoolean.add(true);
        }
         return movieInfoDao.getSelectedAndSortedMovieList(selectEnum, selectValue, sortEnum, sortBoolean);
    }

    @Autowired
    public void setMovieInfoDao(MovieInfoDao movieInfoDao) {
        this.movieInfoDao = movieInfoDao;
    }
}
