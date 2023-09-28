package com.example.mfamail.controller;

import com.example.mfamail.entity.UserEntity;
import com.example.mfamail.repository.UserRepo;
import com.example.mfamail.service.CustomUserDetailService;
import com.example.mfamail.service.TwoFactorAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class RegistrationController {
    @Autowired
    CustomUserDetailService customUserDetailService;
    @Autowired
    TwoFactorAuthService twoFactorAuthService;
    @Autowired
    UserRepo userRepo;

    @PostMapping("/register")
    public String userRegistration(@RequestBody UserEntity user) throws Exception {
        return customUserDetailService.saveUserDetails(user);
    }

    @PostMapping("/register/otp")
    public Boolean checkOtp(@RequestBody UserEntity user,
                            @RequestParam("otp") String otp) {
        UserEntity dummy = userRepo.findByUsername(user.getUsername());
        return twoFactorAuthService.isOtpValid(dummy.getSecret_key(), otp);
    }
}
