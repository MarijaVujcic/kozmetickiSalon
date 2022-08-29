package com.seminar.kozmetickisalon.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.seminar.kozmetickisalon.DTO.RegistrationDTO;
import com.seminar.kozmetickisalon.Model.Role;
import com.seminar.kozmetickisalon.Model.User;
import com.seminar.kozmetickisalon.Repository.RoleRepository;
import com.seminar.kozmetickisalon.Repository.UserRepository;

@Service
public class UserService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
       
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        return new org.springframework.security.core.userdetails.User(
          user.getEmail(), user.getPassword(), enabled, accountNonExpired,
          credentialsNonExpired, accountNonLocked, mapRolesToAuthorities(user.getRoles()));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> collection) {
        return collection.stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());

    }

    public void save(RegistrationDTO userDto, String roleName) {
        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setOneRole(roleRepository.findByName(roleName));
        userRepository.save(newUser);

    }

    public void save(User userDto, String roleName) {
        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setOneRole(roleRepository.findById(Integer.valueOf(roleName)).get());
        userRepository.save(newUser);

    }

    public boolean checkPassword(String pass, User user){
        return passwordEncoder.matches(pass, user.getPassword());
    }

    public User findByEmail(String email) {
        if(userRepository.findByEmail(email).size() != 0)
        {
            return userRepository.findByEmail(email).get(0);
        }
        return null;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteUser(Integer id) {
        userRepository.delete(userRepository.findById(id).get());
    }

    public void updateUser(User user, String role) {
        User updUser =this.findByEmail(user.getEmail());
        updUser.setEmail(user.getEmail());
        updUser.setFirstName(user.getFirstName());
        updUser.setLastName(user.getLastName());
        updUser.setOneRole(roleRepository.findById(Integer.valueOf(role)).get());
        userRepository.save(updUser);
    }

    
}
