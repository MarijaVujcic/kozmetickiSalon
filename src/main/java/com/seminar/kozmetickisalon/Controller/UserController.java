package com.seminar.kozmetickisalon.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seminar.kozmetickisalon.Model.User;
import com.seminar.kozmetickisalon.Repository.RoleRepository;
import com.seminar.kozmetickisalon.Repository.UserRepository;
import com.seminar.kozmetickisalon.Service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/config")
    ModelAndView showUserConfiguration(){
        ModelAndView mv = new ModelAndView("userConfiguration");
        mv.addObject("users", userService.findAll());
        mv.addObject("userToAdd", new User());
        mv.addObject("roles", roleRepository.findAll());
        return mv;
    }

    @GetMapping("/deleteUser/{id}")
    String deleteUser(@PathVariable String id){
        userService.deleteUser(Integer.valueOf(id));
        return "redirect:/user/config";
    }

    @PostMapping("/addUser")
    ModelAndView createNewUser(@ModelAttribute User user, String selectedRole){
        ModelAndView mv = new ModelAndView("userConfiguration");
        mv.addObject("users", userService.findAll());
        mv.addObject("userToAdd", new User());
        mv.addObject("roles", roleRepository.findAll());
        if(userService.findByEmail(user.getEmail()) == null){
            userService.save(user, selectedRole); 
            mv.setViewName("redirect:/user/config");
        }
        mv.addObject("message", "Korisnik već postoji!");
        return mv;
    }

    @PostMapping("/updateUser/")
    ModelAndView updateUserShow(String email){
        ModelAndView mv = new ModelAndView("userConfiguration");
        User updUser = userService.findByEmail(email);
        mv.addObject("users", userService.findAll());
        mv.addObject("userToAdd", new User());
        mv.addObject("roles", roleRepository.findAll());
        mv.addObject("updateUser", updUser);
        return mv;
    } 

    @PostMapping("/updateUser")
    String updateRoleSave(@ModelAttribute User user, String roleToUpdate){
        userService.updateUser(user, roleToUpdate);
        return "redirect:/user/config";
    }
   
    
}
