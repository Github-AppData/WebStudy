package com.example.churchFucTest.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "User")
@AllArgsConstructor
@NoArgsConstructor
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
    private boolean status; // 로그인 상태를 나타냄 (1 로그인, 0 로그아웃)

    @Column(length = 1000, nullable = false)
    private String salt;

    @Column(length = 1000, nullable = false)
    private String email;

    @Column(length = 1000, nullable = false)
    private String address; // 오타 수정됨

    @Column(length = 1000, nullable = false)
    private String detailAddress; // 오타 수정됨

    @Column(length = 1000, nullable = false)
    private String password;

    @Column(length = 1000, nullable = false)
    private String phoneNumber;

    // getters and setters

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
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

    public User(String userId, String username, String roles, Date birthday, Date loginTime, boolean status, String salt, String email, String address, String detailAddress, String password, String phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.roles = roles;
        this.birthday = birthday;
        this.loginTime = loginTime;
        this.status = status;
        this.salt = salt;
        this.email = email;
        this.address = address;
        this.detailAddress = detailAddress;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
