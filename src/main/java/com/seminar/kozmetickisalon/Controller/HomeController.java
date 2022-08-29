package com.seminar.kozmetickisalon.Controller;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seminar.kozmetickisalon.DTO.ReservationDTO;
import com.seminar.kozmetickisalon.Model.Reservations;
import com.seminar.kozmetickisalon.Service.ReservationService;

@Controller
public class HomeController {
    @Autowired
    ReservationService reservationService;


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/")
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
        return mv;
    }

    @PostMapping("/getFreeTime")
    public ModelAndView getTime(Date date) {
         List<String> timeList = new ArrayList<String>();
        timeList.add("9-10");
        timeList.add("10-11");
        timeList.add("11-12");
        timeList.add("12-13");
        timeList.add("13-14");
        timeList.add("14-15");
        timeList.add("15-16");
        timeList.add("16-17");

        ModelAndView mv = new ModelAndView("reserve");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(month + " " + day);
        System.out.println(year + " " +"AAAAAAAAAAAAAAAAAAA");
        List<Reservations>notFree = reservationService.getBuisyTimeDate(month, day);
         
        for(int i=0; i<notFree.size(); i++){
            if(timeList.contains(notFree.get(i).getReservationTime())){
                timeList.remove(notFree.get(i).getReservationTime());
            }
        }
        mv.addObject("freeTime", timeList);
        mv.addObject("dateReservation", date);
        return mv;
    }


}
