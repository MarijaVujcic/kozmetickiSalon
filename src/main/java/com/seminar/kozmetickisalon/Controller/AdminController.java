package com.seminar.kozmetickisalon.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seminar.kozmetickisalon.Model.Employee;
import com.seminar.kozmetickisalon.Model.Role;
import com.seminar.kozmetickisalon.Model.User;
import com.seminar.kozmetickisalon.Repository.*;
import com.seminar.kozmetickisalon.Service.*;

@Controller
@RequestMapping("/nesto")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    OfferService offerService;
    
    
    @GetMapping("/")
    ModelAndView showHomePage(){
        ModelAndView mv = new ModelAndView("roleConfiguration");
        

        return mv;
    }

    @PostMapping("/")
    ModelAndView approveReservation(){
        ModelAndView mv = new ModelAndView("roleConfiguration");
        

        return mv;
    }


    @GetMapping("/user")
    ModelAndView showUserConfiguration(){
        ModelAndView mv = new ModelAndView("userConfiguration");
        mv.addObject("roles", roleRepository.findAll());
        mv.addObject("users", userRepository.findAll());
        mv.addObject("userToAdd", new User());
        return mv;
    }

    @GetMapping("/employee")
    ModelAndView showEmployeeConfiguration(){
        ModelAndView mv = new ModelAndView("employeeConfiguration");
        mv.addObject("offers", offerService.findAll());
        mv.addObject("employees", employeeService.findAll());
        mv.addObject("employeeToAdd", new Employee());
        return mv;
    }

    @GetMapping("/offer")
    ModelAndView showOfferConfiguration(){
        ModelAndView mv = new ModelAndView("offerConfiguration");
        

        return mv;
    }

    @GetMapping("/reservation")
    ModelAndView showReservationConfiguration(){
        ModelAndView mv = new ModelAndView("reservationConfiguration");
        return mv;
    }


    @PostMapping("/user")
    ModelAndView changeUserConfiguration(){
        ModelAndView mv = new ModelAndView("userConfiguration");
        

        return mv;
    }

    @PostMapping("/employee")
    ModelAndView changeEmployeeConfiguration(){
        ModelAndView mv = new ModelAndView("employeeConfiguration");
        

        return mv;
    }

    @PostMapping("/offer")
    ModelAndView changeOfferConfiguration(){
        ModelAndView mv = new ModelAndView("offerConfiguration");
        

        return mv;
    }

    @PostMapping("/reservation")
    ModelAndView changeReservationConfiguration(){
        ModelAndView mv = new ModelAndView("reservationConfiguration");
        return mv;
    }
 
}
