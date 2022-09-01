package com.seminar.kozmetickisalon.Controller;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seminar.kozmetickisalon.DTO.RegistrationDTO;
import com.seminar.kozmetickisalon.DTO.ReservationDTO;
import com.seminar.kozmetickisalon.Model.Reservations;
import com.seminar.kozmetickisalon.Model.User;
import com.seminar.kozmetickisalon.Service.EmployeeService;
import com.seminar.kozmetickisalon.Service.OfferService;
import com.seminar.kozmetickisalon.Service.ReservationService;
import com.seminar.kozmetickisalon.Service.UserService;

@Controller
public class HomeController {
    @Autowired
    ReservationService reservationService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserService userService;
    @Autowired
    OfferService offerService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    @GetMapping("/")
    public String wel(Model model) {
        return "homepagee";
    }

    @GetMapping("/welcome")
    public String Homepage(Model model) {
        return "homepagee";
    }

    @GetMapping("/ponuda")
    public ModelAndView ponuda(Model model) {
        ModelAndView mv = new ModelAndView("ponuda");
        mv.addObject("offers", offerService.findAll());
        return mv;
    }


    @GetMapping("/welcome/reserve")
    public ModelAndView reserve(Model model) {
        ModelAndView mv = new ModelAndView("reserve");
        mv.addObject("employees", employeeService.findAll());
        return mv;
    }

    @PostMapping("/getFreeTime")
    public ModelAndView getTime(String employeeId, Date date){
        ModelAndView mv = new ModelAndView("reserve");
        ReservationDTO newR = new ReservationDTO();
        newR.setDate(date.toString());
        newR.setEmployeeId(Integer.valueOf(employeeId));
        List<String> timeList = reservationService.getFreeTimeForEmployeeDay(employeeId, date);
        mv.addObject("freeTime", timeList);
        mv.addObject("dateReservation", date);
        mv.addObject("reservation", newR);
        mv.addObject("offers", offerService.findAll());
        mv.addObject("employees", employeeService.findAll());
        return mv;
    }

    @PostMapping("/saveReservation")
    public String saveReservation(ReservationDTO reservation, String choosenOffer, String choosenTime) {
        /// spremanje rezervacije
        reservation.setOfferId(Integer.valueOf(choosenOffer));
        reservation.setTime(choosenTime);
        reservationService.saveReservation(reservation);
        return "redirect:/welcome/getUserReservations";
    }

    @GetMapping("/welcome/getUserReservations")
    ModelAndView getUserReservations(){
        ModelAndView mv = new ModelAndView("myReservations");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userService.findByEmail(auth.getName());
        mv.addObject("reservations", reservationService.findAllByUser(u.getUser_id()));
        return mv;
    }

    @GetMapping("/welcome/cancelReservation/{id}")
    String approveReservation(@PathVariable String id){
        reservationService.setcancel(Integer.valueOf(id));
        return "redirect:/welcome/getUserReservations";
    }


}
