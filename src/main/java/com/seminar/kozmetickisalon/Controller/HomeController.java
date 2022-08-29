package com.seminar.kozmetickisalon.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/")
    public String Homepage(Model model) {
        return "Homepage";
    }

    @GetMapping("/ponuda")
    public String ponuda(Model model) {
        return "ponuda";
    }

}
