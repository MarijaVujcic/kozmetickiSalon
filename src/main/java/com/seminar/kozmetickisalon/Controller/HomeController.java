package com.seminar.kozmetickisalon.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {


    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

}
