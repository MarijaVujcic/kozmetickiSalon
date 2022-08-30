package com.seminar.kozmetickisalon.Model;

import javax.persistence.*;


import com.seminar.kozmetickisalon.DTO.RegistrationDTO;

import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer user_id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToOne
    private Role role;
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private Set<Reservations> reservations;

    public User(Integer user_id, String firstName, String lastName, String email, String password, Role role,
            Set<Reservations> reservations) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.reservations = reservations;
        this.role = role;
    }

    public User(String firstName, String lastName, String email, String password, Role role,
            Set<Reservations> reservations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.reservations = reservations;
        this.role = role;
    }

    public User() {
    }

    public User(RegistrationDTO userDto, Role role) {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.role = role;
    }

    public User(User user, Role role) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = role;
    }

    /**
     * @return Integer return the user_id
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * @return String return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return String return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
    



    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }


    /**
     * @return Role return the role
     */
    public Role getRole() {
        return role;
    }

}