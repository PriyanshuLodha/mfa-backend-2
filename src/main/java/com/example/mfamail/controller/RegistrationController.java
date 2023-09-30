package com.example.mfamail.controller;

import com.example.mfamail.entity.LoginEntity;
import com.example.mfamail.entity.QrUserEntity;
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
    public void userRegistration(@RequestBody UserEntity user) throws Exception {
        customUserDetailService.saveUserDetails(user);
    }

    @GetMapping("/register/otp/{username}/{otp}")
    public Boolean checkOtp(@PathVariable("username") String username,
                            @PathVariable("otp") String otp) {
        UserEntity dummy = userRepo.findByUsername(username);
        return twoFactorAuthService.isOtpValid(dummy.getSecret_key(),otp);
    }
    @GetMapping("/register/verified/{username}")
    public String verifyRegistration(@PathVariable(name = "username") String username){
           UserEntity temp=userRepo.findByUsername(username);
        temp.setVerify_email(true);
        userRepo.save(temp);
        return "success";
    }
    @GetMapping("/register/generateQr")
    public String generateQr(@RequestBody UserEntity user){
        UserEntity temp=userRepo.findByUsername(user.getUsername());
        temp.setSecret_key(twoFactorAuthService.generateNewSecret());
        userRepo.save(temp);
        return twoFactorAuthService.generateQrCodeImageUri(temp.getSecret_key());
    }
    @GetMapping("/register/validateOtp")
    public Boolean checkOtp(@RequestBody QrUserEntity validateUser){
        UserEntity temp=userRepo.findByUsername(validateUser.getUsername());
        return twoFactorAuthService.isOtpValid(temp.getSecret_key(),validateUser.getOtp());
    }
    @GetMapping("/register/login")
    public String loginUser(@RequestBody LoginEntity loginEntity){
        UserEntity temp=userRepo.findByUsername(loginEntity.getUsername());
        if(temp==null){
            return new String("User not found");
        }
        return "Success";
    }
}
