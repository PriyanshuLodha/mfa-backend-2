package com.example.mfamail.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    private String username;



    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    private String password;

    private String secret_key=null;
    private Boolean verify_email=false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  Arrays.asList(()->"read");
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
