package ucl.comp0022.team2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ucl.comp0022.team2.model.Movie;
import ucl.comp0022.team2.service.interfaces.Case3Service;

import java.util.List;

@Controller
public class Case3Controller {
    private Case3Service case3Service;

    @ResponseBody
    @RequestMapping("getPopularMoviesByAvgRating/{limitStart}/{limitLength}.do")
    public List<Movie> getPopularMoviesByAvgRating(@PathVariable String limitStart, @PathVariable String limitLength){
        return case3Service.getPopularMoviesAverage(limitStart + ", " + limitLength);
    }

    @ResponseBody
    @RequestMapping("getPopularMoviesByCountRating/{limitStart}/{limitLength}.do")
    public List<Movie> getPopularMoviesByCountRating(@PathVariable String limitStart, @PathVariable String limitLength){
        return case3Service.getPopularMoviesByRating(limitStart + ", " + limitLength);
    }

    @ResponseBody
    @RequestMapping("getPopularMoviesByCountTags/{limitStart}/{limitLength}.do")
    public List<Movie> getPopularMoviesByCountTags(@PathVariable String limitStart, @PathVariable String limitLength){
        return case3Service.getPopularMoviesByTags(limitStart + ", " + limitLength);
    }

    @ResponseBody
    @RequestMapping("getPolarizingMovies/{limitStart}/{limitLength}.do")
    public List<Movie> getPolarizingMovies(@PathVariable String limitStart, @PathVariable String limitLength){
        return case3Service.getPolarizingMovies(limitStart + ", " + limitLength);
    }

    @Autowired
    public void setCase3Service(Case3Service case3Service) {
        this.case3Service = case3Service;
    }
}
