package com.seminar.kozmetickisalon.DTO;

import java.sql.Time;
import java.util.Date;

public class ReservationDTO {

    private String phoneNumber;
    private Integer offerId;
    private Date date;
    private String time;
    public ReservationDTO(String phoneNumber, Integer offerId, Date date, String time) {
        this.phoneNumber = phoneNumber;
        this.offerId = offerId;
        this.date = date;
        this.time = time;
    }

    public ReservationDTO() {
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
     * @return Integer return the offerId
     */
    public Integer getOfferId() {
        return offerId;
    }

    /**
     * @param offerId the offerId to set
     */
    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    /**
     * @return Date return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }




    /**
     * @return String return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

}
