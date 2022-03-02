package ucl.comp0022.team2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ucl.comp0022.team2.model.Movie;
import ucl.comp0022.team2.model.Report;
import ucl.comp0022.team2.service.interfaces.Case2Service;

import java.util.List;

@Controller
public class Case2Controller {
    private Case2Service case2Service;

    @RequestMapping("/getReportsById/{movieId}.do")
    public String getReportsById(Model model, @PathVariable String movieId){
        List<Report> reports =  case2Service.getReports(movieId);
        model.addAttribute("reports", reports);
        return "report";
    }

    @Autowired
    public void setCase2Service(Case2Service case2Service) {
        this.case2Service = case2Service;
    }

}
