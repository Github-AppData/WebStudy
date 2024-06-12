package com.example.churchFucTest.dto;

import java.sql.Date;

public class UserDTO {
    private String userId; // 사용자 식별자(ID)
    private String username; // 사용자 이름
    private String roles; // 사용자 역할
    private Date birthday; // 생일
    private Date loginTime; // 로그인 시간
    private Boolean is_status = false; // 로그인 상태를 나타냄 (1 로그인, 0 로그아웃)
    private String salt;
    private String email;
    private String adress;
    private String datailAdress;
    private String password;
    private String phoneNumber;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Boolean isIs_status() {
        return is_status;
    }

    public void setIs_status(Boolean is_status) {
        this.is_status = is_status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDatailAdress() {
        return datailAdress;
    }

    public void setDatailAdress(String datailAdress) {
        this.datailAdress = datailAdress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserDTO(String userId, String username, String roles, Date birthday, Date loginTime, Boolean is_status, String salt, String email, String adress, String datailAdress, String password, String phoneNumber) {
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
}
