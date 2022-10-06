package lt.knygynas.Knygu.rezervavimas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    String namuPuslapis(Model model){
        return "homePage.html";
    }
}
