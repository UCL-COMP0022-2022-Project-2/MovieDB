package ucl.comp0022.team2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    //The default page to open. Now set to homepage.jsp
    @RequestMapping("/")
    public String defaultPage(){
        return "homepage";
    }

    @RequestMapping("/example.html")
    public String example(){
        return "example";
    }

    @RequestMapping("/homepage.html")
    public String homepage(){
        return "homepage";
    }

    @RequestMapping("/popular.html")
    public String popular(){
        return "popular";
    }

    @RequestMapping("/polarizing.html")
    public String polarizing(){
        return "polarizing";
    }

    @RequestMapping("/predict.html")
    public String predict(){
        return "predict";
    }
}
