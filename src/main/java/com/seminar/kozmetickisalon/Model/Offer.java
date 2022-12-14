package com.seminar.kozmetickisalon.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Offer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "offer_id")
    private Integer offer_id;

    private String nameOfOffer;
    private Float price;

    @OneToMany(mappedBy="offer", fetch = FetchType.EAGER,
    cascade = CascadeType.ALL)
    private Set<Reservations> reservations = new HashSet<Reservations>();

    public Offer(Integer offer_id, String nameOfOffer, Float price,
            Set<Reservations> reservations) {
        this.offer_id = offer_id;
        this.nameOfOffer = nameOfOffer;
        this.price = price;
        this.reservations = reservations;
    }
    public Offer() {
    }
    public Offer(String nameOfOffer, Float price, Set<Reservations> reservations) {
        this.nameOfOffer = nameOfOffer;
        this.price = price;
        this.reservations = reservations;
    }



    public Offer(Offer offer) {
        this.nameOfOffer = offer.getNameOfOffer();
        this.price = offer.getPrice();
    }

    /**
     * @return Integer return the offer_id
     */
    public Integer getOffer_id() {
        return offer_id;
    }

    /**
     * @param offer_id the offer_id to set
     */
    public void setOffer_id(Integer offer_id) {
        this.offer_id = offer_id;
    }

    /**
     * @return String return the nameOfOffer
     */
    public String getNameOfOffer() {
        return nameOfOffer;
    }

    /**
     * @param nameOfOffer the nameOfOffer to set
     */
    public void setNameOfOffer(String nameOfOffer) {
        this.nameOfOffer = nameOfOffer;
    }


    /**
     * @return Float return the price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * @return Set<Reservations> return the reservations
     */
    public Set<Reservations> getReservations() {
        return reservations;
    }

    /**
     * @param reservations the reservations to set
     */
    public void setReservations(Set<Reservations> reservations) {
        this.reservations = reservations;
    }

}
