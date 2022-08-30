package com.seminar.kozmetickisalon.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Reservations newR = new Reservations();
        newR.setIsConfirmed(false);
        newR.setIsCancled(false);

        newR.setOffer(offerRepository.findById(r.getOfferId()).get());
        newR.setUser(userRepository.findByEmail(loggedInUser.getName()).get(0));
        newR.setPhoneNumber(r.getPhoneNumber());
        newR.setReservationDate(r.getDate());
        newR.setReservationTime(r.getTime());
        newR.setEmployee(employeeService.findById(r.getEmployeeId()));
        reservationRepository.save(newR);
    }


    public List<Reservations> getBuisyTimeDate(int month, int day) {
        List<Reservations> reservation = this.findAll();
        List<Reservations> reservationFree = new ArrayList<Reservations>();
        reservation.forEach((r) ->{
            Calendar calendar = new GregorianCalendar();
            calendar.setTime( r.getReservationDate());
            int monthR = calendar.get(Calendar.MONTH) + 1;
            int dayR = calendar.get(Calendar.DAY_OF_MONTH);
            
            if(dayR == day && monthR == month && r.isIsConfirmed()){
                reservationFree.add(r);

            }
        });
        return reservationFree;
    }

    public List<Reservations> getBuisyTimeDateEmployee(int month, int day, Employee e) {
        List<Reservations> reservation = this.findAll();
        List<Reservations> resB = new ArrayList<Reservations>();
        reservation.forEach((r) ->{
            Calendar calendar = new GregorianCalendar();
            calendar.setTime( r.getReservationDate());
            int monthR = calendar.get(Calendar.MONTH) + 1;
            int dayR = calendar.get(Calendar.DAY_OF_MONTH);
            
            if(dayR == day && monthR == month && r.isIsConfirmed() && r.getEmployee().equals(e)){
                resB.add(r);
            }
        });
        return resB;
    }


    

    
}
