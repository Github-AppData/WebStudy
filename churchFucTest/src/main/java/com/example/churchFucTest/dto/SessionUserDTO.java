package com.example.churchFucTest.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class SessionUserDTO {

    private Long idx;
    private String userId; // 사용자 식별자(ID)
    private String username; // 사용자 이름
    private String roles; // 사용자 역할 목록
    private Date loginTime; // 로그인 시간
    private boolean is_status; // 로그인 상태를 나타냄 (0 로그인, 1 로그아웃)
    private String password;
    private String salt;
    private String email;
    private String detailAdress;
    private String adress;
    private String phoneNumber;
    private Date birthday;


    // 기본 생성자
    public SessionUserDTO() {
    }

    public SessionUserDTO(String userId, String username, String roles, Date loginTime, boolean is_status, String password, String salt, String email, String detailAdress, String adress, String phoneNumber, Date birthday) {
        this.userId = userId;
        this.username = username;
        this.roles = roles;
        this.loginTime = loginTime;
        this.is_status = is_status;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.detailAdress = detailAdress;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }
}
