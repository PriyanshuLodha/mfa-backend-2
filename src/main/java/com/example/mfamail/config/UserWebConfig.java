package com.example.mfamail.config;

import com.example.mfamail.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class UserWebConfig extends WebSecurityConfigurerAdapter {
    @Autowired UserWebConfig userWebConfig;


    @Autowired CustomUserDetailService customUserDetailService;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/register").permitAll().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    public interface AuthenticationProvider {
        Authentication authenticate(Authentication authentication) throws AuthenticationException;
        boolean supports(Class<?> authentication);
    }
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(customUserDetailService);

        return authenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
