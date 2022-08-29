package com.seminar.kozmetickisalon.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seminar.kozmetickisalon.Model.User;
import com.seminar.kozmetickisalon.Service.UserService;
@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    @GetMapping("/welcome")
    public String home(Model model) {
        return "homepagee";
    }
    @ModelAttribute("userLogin")
    public User userLogin() {
        return new User();
    }

    @PostMapping("/process-login")
    public ModelAndView Homepage(@ModelAttribute User userLogin, BindingResult result) {
        ModelAndView mv = new ModelAndView("login");
        if (result.hasErrors()){
            return mv;
        }
        
        User existing = userService.findByEmail(userLogin.getEmail());
        if (existing == null){
            mv.addObject("message", "User doesnt exist!");
            return mv;
        }else{
            if(!userService.checkPassword(userLogin.getPassword(),existing)){
                mv.addObject("message", "Password disnt correct!");
                return mv;
            }
        }

        mv.setViewName("homepagee");
        return mv;
    }

    @GetMapping("/ponuda")
    public String ponuda(Model model) {
        return "ponuda";
    }

}
