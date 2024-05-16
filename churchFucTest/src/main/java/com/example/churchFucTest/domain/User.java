package com.example.churchFucTest.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "User")
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 1000, nullable = false)
    private String userId; // 사용자 식별자(ID)

    @Column(length = 1000, nullable = false)
    private String username; // 사용자 이름

    @Column(length = 1000, nullable = false)
    private String roles; // 사용자 역할

    @Column(length = 1000, nullable = false)
    private Date birthday; // 생일

    @Temporal(TemporalType.DATE)
    private Date loginTime; // 로그인 시간

    @Column(columnDefinition = "boolean default 0")
    private boolean is_status; // 로그인 상태를 나타냄 (1 로그인, 0 로그아웃)

    @Column(length = 1000, nullable = false)
    private String salt;

    @Column(length = 1000, nullable = false)
    private String email;

    @Column(length = 1000, nullable = false)
    private String adress;

    @Column(length = 1000, nullable = false)
    private String datailAdress;

    @Column(length = 1000, nullable = false)
    private String password;

    @Column(length = 1000, nullable = false)
    private String phoneNumber;


    public User(String userId, String username, String roles, Date birthday, Date loginTime, boolean is_status, String salt, String email, String adress, String datailAdress, String password, String phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.roles = roles;
        this.birthday = birthday;
        this.loginTime = loginTime;
        this.is_status = is_status;
        this.salt = salt;
        this.email = email;
        this.adress = adress;
        this.datailAdress = datailAdress;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }

}
