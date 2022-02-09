package ucl.comp0022.team2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    //The default page to open. Now set to example.jsp
    @RequestMapping("/")
    public String defaultPage(){
        return "example";
    }

    @RequestMapping("/example.html")
    public String example(){
        return "example";
    }

    @RequestMapping("/homepage.html")
    public String homepage(){
        return "homepage";
    }
}
