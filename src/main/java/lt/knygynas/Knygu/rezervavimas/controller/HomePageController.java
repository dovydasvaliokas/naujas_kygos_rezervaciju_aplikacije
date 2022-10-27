package lt.knygynas.Knygu.rezervavimas.controller;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class HomePageController {

    @GetMapping("/")
    String namuPuslapis(Model model, Principal principal){
        principal.getName();
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println(authorities);
        System.out.println(principal.getName());
        return "homePage.html";
    }

}
