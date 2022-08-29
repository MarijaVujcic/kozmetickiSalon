package com.seminar.kozmetickisalon.Controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.seminar.kozmetickisalon.DTO.RegistrationDTO;
import com.seminar.kozmetickisalon.Model.User;
import com.seminar.kozmetickisalon.Service.UserService;


@Controller
public class RegistrationController {
    
    @Autowired
    UserService userService;

    
    @ModelAttribute("user")
    public RegistrationDTO userRegistrationDto() {
        return new RegistrationDTO();
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        return "registration";
    }



    @PostMapping("/registration")
    public String registerUserAccount( @Valid @ModelAttribute("user") RegistrationDTO userDto, BindingResult result) {

        if (result.hasErrors()){
            return "registration";
        }
        
        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email, try to log in!");
            return "registration";
        }

        userService.save(userDto, "ROLE_ADMIN");
        return "redirect:/login";

    }
}