package com.seminar.kozmetickisalon.Model;

import java.util.Set;
import javax.persistence.*;


@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Integer employee_id;

    private String name;
    
    @ManyToMany(cascade = {
        CascadeType.ALL
    })
    @JoinTable(
        name = "employees_projects",
        joinColumns =@JoinColumn(name = "employeeId", referencedColumnName = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "offerId", referencedColumnName = "offer_id" )
        
    )
    private Set<Offer> offers;

    public Employee() {
    }

    public Employee(Integer employee_id, String name, Set<Offer> offers) {
        this.employee_id = employee_id;
        this.name = name;
        this.offers = offers;
    }

    public Employee(String name, Set<Offer> offers) {
        this.name = name;
        this.offers = offers;
    }

    


    /**
     * @return Integer return the employee_id
     */
    public Integer getEmployee_id() {
        return employee_id;
    }

    /**
     * @param employee_id the employee_id to set
     */
    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Set<Offer> return the offers
     */
    public Set<Offer> getOffers() {
        return offers;
    }

    /**
     * @param offers the offers to set
     */
    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

}
