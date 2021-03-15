package zenab.project.customerRelationshipManager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

    @RequestMapping("/welcome.html")
    public ModelAndView firstPage() {
        return new ModelAndView("welcome");
    }

}