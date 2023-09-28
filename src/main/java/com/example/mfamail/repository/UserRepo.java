package com.example.mfamail.repository;

import com.example.mfamail.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {

    UserEntity findByUsername(String name);
}
