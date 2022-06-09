package com.miniproject.BioskopKampung.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Autowired
    public AppSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/booking-ticket").hasRole("Admin")
                .antMatchers("/category/insert-new-category").hasRole("Admin")
                .antMatchers("/customer").hasRole("Admin")
                .antMatchers("/film/insert-new-film").hasRole("Admin")
                .antMatchers("/schedule/insert-new-schedule").hasRole("Admin")
                .antMatchers("/seats/insert-new-seats").hasRole("Admin")
                .antMatchers("/studio", "/studio/insert-new-studio").hasRole("Admin")
                // ================================================================================
                .antMatchers("/booking-ticket/booking").hasRole("User")
                .antMatchers("/category").hasRole("User")
                .antMatchers("/customer/insert-new-customer").hasRole("User")
                .antMatchers("/film").hasRole("User")
                .antMatchers("/schedule").hasRole("User")
                .antMatchers("/seats").hasRole("User")
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }
}
