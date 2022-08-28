package com.seminar.kozmetickisalon.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.seminar.kozmetickisalon.Service.UserService;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(
                            "/",
                            "/registration**", 
                            "/js/**",
                            "/css/**",
                            "/images/**",
                            "/webjars/**").permitAll()
                .and()
                    .formLogin()
                        .loginPage("/login")
                            .successHandler(customSuccessHandler)
                            .permitAll()
                            
                .and()
                    .logout()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutUrl("/logout")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll();
        http    .authorizeRequests()
                    .antMatchers("/admin/*").access("hasRole('ADMIN')")
                    .antMatchers("/role/*").access("hasRole('ADMIN')")
                    .antMatchers("/user/*").access("hasRole('ADMIN')")
                    .antMatchers("/reservations/*").access("hasRole('ADMIN')")
                    .antMatchers("/offer/*").access("hasRole('ADMIN')")
                    .antMatchers("/employee/*").access("hasRole('ADMIN')")
                    .antMatchers("/welcome/*").access("hasRole('USER')")
                
                ;
                
                
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProviderUser(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProviderUser());

    }



}