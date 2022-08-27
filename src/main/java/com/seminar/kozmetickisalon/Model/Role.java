package com.seminar.kozmetickisalon.Model;



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
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer role_id;
    private String name;

    /**
     *
     */
    @OneToMany(mappedBy="role", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private  Set<User> users;

    public Role(Integer role_id, String name, Set<User> users) {
        this.role_id = role_id;
        this.name = name;
        this.users = users;
    }

    public Role() {
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

   


}
