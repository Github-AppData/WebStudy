package com.example.churchFucTest.service;


import com.example.churchFucTest.domain.User;
import com.example.churchFucTest.dto.SessionUserDTO;
import com.example.churchFucTest.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final UserRepository userRepository;

    private final HttpServletRequest request;

    /**
     * 로그인 체크
     */
    public SessionUserDTO processLogin(String userId){

        boolean is_exists;

        // 전달받은 userid로 로그인 하려는 user가 DB에 있는 지 체크
        is_exists = userRepository.existsByUserId(userId);

        // UserRepository를 사용하여 사용자 정보를 가져옴
        User user = userRepository.findByUserId(userId);

        SessionUserDTO sessionUserDTO = null;

        if(is_exists){ // id가 DB에 있으면,,
            // 상태를 로그인 상태로 바꾼다.
            user.set_status(true);

            // 로그인 할 때마다, 새로운 세션 생성
            sessionUserDTO = new SessionUserDTO();

            sessionUserDTO.setUserId(user.getUserId());
            sessionUserDTO.setUsername(user.getUsername());
            sessionUserDTO.setRoles(user.getRoles());
            sessionUserDTO.setLoginTime(user.getLoginTime());
            sessionUserDTO.set_status(user.is_status());
            sessionUserDTO.setIdx(user.getIdx());


        } // 없으면 null 임.

        return sessionUserDTO;
    }

}
