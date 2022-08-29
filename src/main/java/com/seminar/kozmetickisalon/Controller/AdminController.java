package com.seminar.kozmetickisalon.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seminar.kozmetickisalon.Model.Employee;
import com.seminar.kozmetickisalon.Model.User;
import com.seminar.kozmetickisalon.Repository.*;
import com.seminar.kozmetickisalon.Service.*;

@Controller
@RequestMapping("/admin")
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
        ModelAndView mv = new ModelAndView("reservationsList");
        mv.addObject("reservations",  reservationService.finAllUnconfirmed());
        return mv;
    }

    @GetMapping("/confirmReservation/{id}")
    String approveReservation(@PathVariable String id){
        reservationService.setConfirmed(Integer.valueOf(id));
        return "redirect:/admin/";
    }


    
 
}
