package com.seminar.kozmetickisalon.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
        User user = userRepository.findByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<Role> roles = new ArrayList<Role>();
        roles.add(user.getRole());
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        return new org.springframework.security.core.userdetails.User(
          user.getEmail(), user.getPassword().toLowerCase(), enabled, accountNonExpired,
          credentialsNonExpired, accountNonLocked, mapRolesToAuthorities(roles));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());

    }

    public void save(RegistrationDTO userDto, String roleName) {
        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setRole(roleRepository.findByName(roleName));
        userRepository.save(newUser);

    }

    public void save(User userDto, String roleName) {
        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setRole(roleRepository.findById(Integer.valueOf(roleName)).get());
        userRepository.save(newUser);

    }

    public boolean checkPassword(String pass, User user){
        return passwordEncoder.matches(pass, user.getPassword());
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
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
        updUser.setRole(roleRepository.findById(Integer.valueOf(role)).get());
        userRepository.save(updUser);
    }

    
}
