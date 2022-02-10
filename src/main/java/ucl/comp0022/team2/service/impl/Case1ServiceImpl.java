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
        return null;
        // return movieInfoDao.getSelectedAndSortedMovieList(0, "", 0, true);
    }

    /**
     * @inheritDoc
     * TODO: accomplish multiple selectors. Currently only enabled single selector
     */
    @Override
    public List<Movie> getMovies(String[] selectParams, String[] sortParams) {
        int selectEnum = 0;
        String selectValue = "";
        int sortEnum = 0;
        boolean sortBoolean = true;
        for(int i = 0; i < selectParams.length; i++){
            if(!selectParams[i].equals("")){
                selectEnum = i+1;
                selectValue = selectParams[i];
                break;
            }
        }
        if(!sortParams[0].equals("")){
            switch (sortParams[0]){
                case "title":
                    sortEnum = 1;
                case "rating":
                    sortEnum = 2;
                case "year":
                    sortEnum = 3;
            }
        }
        if(sortParams[1].equals("desc")){
            sortBoolean = false;
        }
        return null;
        // return movieInfoDao.getSelectedAndSortedMovieList(selectEnum, selectValue, sortEnum, sortBoolean);
    }

    @Autowired
    public void setMovieInfoDao(MovieInfoDao movieInfoDao) {
        this.movieInfoDao = movieInfoDao;
    }
}
