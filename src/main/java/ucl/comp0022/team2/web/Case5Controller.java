package ucl.comp0022.team2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ucl.comp0022.team2.model.Personality;
import ucl.comp0022.team2.service.interfaces.Case5Service;
import ucl.comp0022.team2.service.interfaces.Case6Service;

import java.util.HashMap;
import java.util.List;

@Controller
public class Case5Controller {

    private Case5Service case5Service;

    @RequestMapping("/getAllGenres.do")
    @ResponseBody
    public List<String> getAllGenres(){
        case5Service.initialize();
        return case5Service.getAllGenres();
    }

    @RequestMapping("/getRatingsByGenres.do")
    @ResponseBody
    public HashMap<String, Personality> getRatingsByGenres(String[] genres){
        return case5Service.getPersonalitiesByGenres(genres);
    }

    @RequestMapping("/getTotalGenresAverageRatings.do")
    @ResponseBody
    public Personality getTotalGenresAverageRatings(){
        return case5Service.getAllGenresAveragePersonality();
    }

    @Autowired
    public void setCase5Service(Case5Service case5Service) {
        this.case5Service = case5Service;
    }
}
