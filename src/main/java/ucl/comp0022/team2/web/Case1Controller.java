package ucl.comp0022.team2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ucl.comp0022.team2.model.Movie;
import ucl.comp0022.team2.service.interfaces.Case1Service;

import java.util.Arrays;
import java.util.List;

@Controller
public class Case1Controller {

    private Case1Service case1Service;


    @RequestMapping("/getRequiredMovies.do")
    @ResponseBody
    public List<Movie> getRequiredMovies(String[] selectParams, String[] sortParams) {
        return case1Service.getMovies(selectParams, sortParams);
    }

    @RequestMapping("/getMoviesCount.do")
    @ResponseBody
    public Integer getMoviesCount(String[] selectParams, String[] sortParams) {
        return case1Service.getMoviesCount(selectParams, sortParams);
    }

    @Autowired
    public void setCase1Service(Case1Service case1Service) {
        this.case1Service = case1Service;
    }


}
