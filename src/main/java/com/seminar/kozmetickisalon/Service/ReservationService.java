package com.seminar.kozmetickisalon.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.seminar.kozmetickisalon.DTO.ReservationDTO;
import com.seminar.kozmetickisalon.Model.Employee;
import com.seminar.kozmetickisalon.Model.Reservations;
import com.seminar.kozmetickisalon.Repository.OfferRepository;
import com.seminar.kozmetickisalon.Repository.ReservationRepository;
import com.seminar.kozmetickisalon.Repository.UserRepository;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    OfferRepository offerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeService employeeService;

    public List<Reservations> findAll() {
        return reservationRepository.findAll();
    }

    public List<String> getFreeTimeForEmployeeDay(String employeeId, Date date){
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
        List<Reservations>emplRes = this.getBuisyTimeDateEmployee(month, day,employeeService.findById(Integer.valueOf(employeeId)));
         
        for(int i=0; i<emplRes.size(); i++){
            if(timeList.contains(emplRes.get(i).getReservationTime())){
                timeList.remove(emplRes.get(i).getReservationTime());
            }
        }
       
        return timeList;
    }

    public List<Reservations> findAllByUser(Integer u) {
        List<Reservations> res = new ArrayList<Reservations>();
        this.findAll().forEach((r) -> {
            if(r.getUser().getUser_id() == u){
                res.add(r);
            }
        });
        return res;
    }
    
    public List<Reservations> findAllByOffer(Integer o) {
        List<Reservations> res = new ArrayList<Reservations>();
        this.findAll().forEach((r) -> {
            if(r.getOffer().getOffer_id() == o){
                res.add(r);
            }
        });
        return res;
    }


    public void delete(Integer valueOf) {
        reservationRepository.delete(reservationRepository.findById(valueOf).get());
    }


    public List<Reservations> finAllUnconfirmed() {
        List<Reservations> res = new ArrayList<Reservations>();
        this.findAll().forEach((r) -> {
            if(!r.isIsConfirmed()){
                res.add(r);
            }
        });
        return res;
    }
  

    public void setConfirmed(Integer valueOf) {
        Reservations r = reservationRepository.findById(valueOf).get();
        r.setIsConfirmed(true);
        reservationRepository.save(r);
    }

    public void saveReservation(ReservationDTO r){
        String str1 = r.getDate();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("EE MMM dd HH:mm:ss zzzz yyyy", Locale.US);
        try {
             date = formatter.parse(str1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
       
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Reservations newR = new Reservations();
        newR.setIsConfirmed(false);
        newR.setIsCancled(false);

        newR.setOffer(offerRepository.findById(r.getOfferId()).get());
        newR.setUser(userRepository.findByEmail(loggedInUser.getName()).get(0));
        newR.setPhoneNumber(r.getPhoneNumber());
        newR.setReservationDate(date);
        newR.setReservationTime(r.getTime());
        newR.setEmployee(employeeService.findById(r.getEmployeeId()));
        reservationRepository.save(newR);
    }

    public List<Reservations> getBuisyTimeDateEmployee(int month, int day, Employee e) {
        List<Reservations> reservation = this.findAll();
        List<Reservations> resB = new ArrayList<Reservations>();
        reservation.forEach((r) ->{
            Calendar calendar = new GregorianCalendar();
            calendar.setTime( r.getReservationDate());
            int monthR = calendar.get(Calendar.MONTH) + 1;
            int dayR = calendar.get(Calendar.DAY_OF_MONTH);
            
            if(dayR == day && monthR == month && r.isIsConfirmed() && r.getEmployee() != null && r.getEmployee().equals(e)){
                resB.add(r);
            }
        });
        return resB;
    }


    public void setcancel(Integer valueOf) {
        Reservations r = reservationRepository.findById(valueOf).get();
        r.setIsCancled(true);
        reservationRepository.save(r);
    }

    public List<Reservations> findAllByEmployee(Employee e) {
        List<Reservations> reservation = this.findAll();
        List<Reservations> resB = new ArrayList<Reservations>();
        reservation.forEach((r) ->{
            if( r.getEmployee() != null && r.getEmployee().equals(e)){
                resB.add(r);
            }
        });
        return resB;
    }


    

    
}
