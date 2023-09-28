package com.example.mfamail.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "mail_token")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiredAt;
    @ManyToOne
    @JoinColumn(name="token_user_id")
    private UserEntity userEntity;
    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, UserEntity user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }
}
