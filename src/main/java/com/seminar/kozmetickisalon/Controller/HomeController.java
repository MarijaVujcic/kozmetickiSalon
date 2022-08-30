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
    public String ponuda(Model model) {
        return "ponuda";
    }


    @GetMapping("/reserve")
    public ModelAndView reserve(Model model) {
        ModelAndView mv = new ModelAndView("reserve");
        mv.addObject("employees", employeeService.findAll());
        return mv;
    }

    @PostMapping("/getFreeTime")
    public ModelAndView getTime(String employeeId, Date date){
        ModelAndView mv = new ModelAndView("reserve");
        Calendar calendar = new GregorianCalendar();
        List<String> timeList = new ArrayList<String>();
        timeList.add("9-10");
        timeList.add("10-11");
        timeList.add("11-12");
        timeList.add("12-13");
        timeList.add("13-14");
        timeList.add("14-15");
        timeList.add("15-16");
        timeList.add("16-17");
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("AAAAAAAAAAAAAAAAAAAAA  " + month + " " + "day" + employeeId);
        List<Reservations>emplRes = reservationService.getBuisyTimeDateEmployee(month, day,employeeService.findById(Integer.valueOf(employeeId)));
         
        for(int i=0; i<emplRes.size(); i++){
            if(timeList.contains(emplRes.get(i).getReservationTime())){
                timeList.remove(emplRes.get(i).getReservationTime());
            }
        }
        ReservationDTO newR = new ReservationDTO();
        newR.setDate(date);
        newR.setEmployeeId(Integer.valueOf(employeeId));
        mv.addObject("freeTime", timeList);
        mv.addObject("dateReservation", date);
        mv.addObject("reservation", newR);
        mv.addObject("offers", offerService.findAll());
        mv.addObject("employees", employeeService.findAll());
        return mv;
    }

    @PostMapping("/saveReservation")
    public String saveReservation(ReservationDTO reservation) {
       
        /// stvaranje rezervacije
        reservationService.saveReservation(reservation);
        return "redirect:/getUserReservations";
    }

    @GetMapping("/getUserReservations")
    ModelAndView getUserReservations(){
        ModelAndView mv = new ModelAndView("myReservations");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userService.findByEmail(auth.getName());
        mv.addObject("reservations", reservationService.findAllByUser(u.getUser_id()));
        return mv;

    }


}
