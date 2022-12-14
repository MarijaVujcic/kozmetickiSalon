package com.seminar.kozmetickisalon.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservations {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    private Integer reservation_id;

    private Date reservationDate;
    private String reservationTime;
    private String phoneNumber;
    private Float totalPrice;
    private boolean isConfirmed = false;
    private boolean isCancled = false;

    @ManyToOne
    private User user;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="offer_id", nullable=false)
    private Offer offer;


    public Reservations() {
    }

    public Reservations(Date reservationDate, String phoneNumber, Float totalPrice, User user, Offer offer) {
        this.reservationDate = reservationDate;
        this.phoneNumber = phoneNumber;
        this.totalPrice = totalPrice;
        this.user = user;
        this.offer = offer;
        this.isConfirmed = false;

    }

    /**
     * @return Integer return the reservation_id
     */
    public Integer getReservation_id() {
        return reservation_id;
    }

    /**
     * @param reservation_id the reservation_id to set
     */
    public void setReservation_id(Integer reservation_id) {
        this.reservation_id = reservation_id;
    }

    /**
     * @return Date return the reservationDate
     */
    public Date getReservationDate() {
        return reservationDate;
    }

    /**
     * @param reservationDate the reservationDate to set
     */
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    /**
     * @return String return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return Float return the totalPrice
     */
    public Float getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return Offer return the offer
     */
    public Offer getOffer() {
        return offer;
    }

    /**
     * @param offer the offer to set
     */
    public void setOffer(Offer offer) {
        this.totalPrice = offer.getPrice();
        this.offer = offer;
    }


    /**
     * @return boolean return the isConfirmed
     */
    public boolean isIsConfirmed() {
        return isConfirmed;
    }

    /**
     * @param isConfirmed the isConfirmed to set
     */
    public void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    /**
     * @return boolean return the isCancled
     */
    public boolean isIsCancled() {
        return isCancled;
    }

    /**
     * @param isCancled the isCancled to set
     */
    public void setIsCancled(boolean isCancled) {
        this.isCancled = isCancled;
    }
    
    


    /**
     * @return String return the reservationTime
     */
    public String getReservationTime() {
        return reservationTime;
    }

    /**
     * @param reservationTime the reservationTime to set
     */
    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }


    /**
     * @return Employee return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
