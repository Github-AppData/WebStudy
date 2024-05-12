package com.example.churchFucTest.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SessionUser {
    private Long userId; // 사용자 식별자(ID)
    private String username; // 사용자 이름
    private List<String> roles; // 사용자 역할 목록
    private LocalDateTime loginTime; // 로그인 시간


    // 기본 생성자
    public SessionUser() {
    }

    public SessionUser(Long userId, String username, List<String> roles, LocalDateTime loginTime) {
        this.userId = userId;
        this.username = username;
        this.roles = roles;
        this.loginTime = loginTime;
    }
}
