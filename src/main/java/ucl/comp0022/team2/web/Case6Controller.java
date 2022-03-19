package ucl.comp0022.team2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ucl.comp0022.team2.model.Personality;
import ucl.comp0022.team2.service.interfaces.Case6Service;

import java.util.HashMap;
import java.util.List;

@Controller
public class Case6Controller {

    private Case6Service case6Service;

    @RequestMapping("getTagsByInitialLetter/{letter}.do")
    @ResponseBody
    public List<String> getTagsByInitialLetter(@PathVariable char letter){
        return case6Service.getTagsByFirstLetter(letter);
    }

    @RequestMapping("getRatingsByTags.do")
    @ResponseBody
    public HashMap<String, Personality> getRatingsByTags(String[] tags){
        return case6Service.getPersonalitiesByTags(tags);
    }

    @RequestMapping("getTotalTagsAverageRatings.do")
    @ResponseBody
    public Personality getTotalTagsAverageRatings(){
        return case6Service.getAllTagsAveragePersonality();
    }

    @Autowired
    public void setCase6Service(Case6Service case6Service) {
        this.case6Service = case6Service;
    }
}
