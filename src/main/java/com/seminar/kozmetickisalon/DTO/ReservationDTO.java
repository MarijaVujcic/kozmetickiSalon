package com.seminar.kozmetickisalon.DTO;

import java.util.Date;

public class ReservationDTO {

    private String phoneNumber;
    private Integer offerId;
    private String date;
    private String time;
    private Integer employeeId;
    public ReservationDTO(String phoneNumber, Integer offerId, String date, String time) {
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


    /**
     * @return Integer return the employeeId
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
