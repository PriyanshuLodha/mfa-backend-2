package com.example.mfamail.service;

import com.example.mfamail.entity.ConfirmationToken;
import com.example.mfamail.repository.ConfirmationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService {
    @Autowired
    ConfirmationTokenRepo confirmationTokenRepo;
    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepo.save(confirmationToken);
    }
}
