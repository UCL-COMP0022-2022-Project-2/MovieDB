package ucl.comp0022.team2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ucl.comp0022.team2.service.interfaces.Case4Service;

@Controller
public class Case4Controller {

    private Case4Service case4Service;

    @RequestMapping("getPredictedScoreByMovieId/{movieId}")
    @ResponseBody
    public double getPredictedScoreByMovieId(@PathVariable Integer movieId){
        return case4Service.getPredictedScoreByMovieId(movieId);
    }

    @Autowired
    public void setCase4Service(Case4Service case4Service) {
        this.case4Service = case4Service;
    }

}
