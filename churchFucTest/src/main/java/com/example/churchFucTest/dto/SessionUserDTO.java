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


    // 기본 생성자
    public SessionUserDTO() {
    }

    public SessionUserDTO(Long idx, String userId, String username, String roles, Date loginTime, boolean is_status) {
        this.idx = idx;
        this.userId = userId;
        this.username = username;
        this.roles = roles;
        this.loginTime = loginTime;
        this.is_status = is_status;
    }
}
