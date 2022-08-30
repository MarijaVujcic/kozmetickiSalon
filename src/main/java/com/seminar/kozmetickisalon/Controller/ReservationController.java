package com.seminar.kozmetickisalon.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seminar.kozmetickisalon.Service.ReservationService;
import com.seminar.kozmetickisalon.Service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seminar.kozmetickisalon.Service.OfferService;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
    
    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @Autowired
    OfferService offerService;

    @GetMapping("/config")
    ModelAndView showReservationsConfiguration(){
        ModelAndView mv = new ModelAndView("reservationConfiguration");
        mv.addObject("offers", offerService.findAll());
        mv.addObject("users", userService.findAll());
        mv.addObject("reservations", reservationService.findAll());
        return mv;
    }

    @PostMapping("/getByOffers/")
    ModelAndView offers( String id){
        ModelAndView mv = new ModelAndView("reservationConfiguration");
        mv.addObject("offers", offerService.findAll());
        mv.addObject("users", userService.findAll());
        mv.addObject("reservations", reservationService.findAllByOffer(Integer.valueOf(id)));
        return mv;
    }
    @PostMapping("/getByUsers/")
    ModelAndView users(String id){
        ModelAndView mv = new ModelAndView("reservationConfiguration");
        mv.addObject("offers", offerService.findAll());
        mv.addObject("users", userService.findAll());
        mv.addObject("reservations", reservationService.findAllByUser(Integer.valueOf(id)));
        return mv;
    }

    @GetMapping("/deleteReservation/{id}")
    String delete(@PathVariable String id){
        reservationService.delete(Integer.valueOf(id));
        return "redirect:/reservation/config";
    }


}
