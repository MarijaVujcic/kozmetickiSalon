package com.seminar.kozmetickisalon.Model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Integer employee_id;

    private String name;
    
    @OneToMany(mappedBy="employee", fetch = FetchType.EAGER,
    cascade = CascadeType.ALL)
    private Set<Reservations> reservations;
  
    public Employee() {
    }

    public Employee(Integer employee_id, String name) {
        this.employee_id = employee_id;
        this.name = name;
    }

    public Employee(String name) {
        this.name = name;

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

    public Set<Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservations> reservations) {
        this.reservations = reservations;
    }


}
