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
public class LoginAndAuthService {

    private final UserRepository userRepository;

    private final HttpServletRequest request;

    /** 로그인 처리 - 세션에 user 정보 등록 */
    public SessionUserDTO processLogin(String userId){

        boolean is_exists;

        is_exists = userRepository.existsByUserId(userId);
        User user = userRepository.findByUserId(userId);

        // 로그인 세션 DTO임.
        SessionUserDTO sessionUserDTO = null;

        if(is_exists){ // id가 DB에 있으면,,

            user.set_status(true); // 상태를 로그인 상태로 바꾼다.
            userRepository.save(user); // 바꾼 상태를 저장.

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

    /** 회원가입 처리 - 정보저장 및 솔트생성, 비밀번호 암호화 */
    public void processsignUp(User user){




    }

    /** userid를 인수로 받아, 관리자가 맞는지 체크 */
    public void checkUserOrNotAdmin(String userid){

        // TODO : userid를 받아와서, id와 비밀번호를 check 한다.
        User user = userRepository.findByUserId(userid);


    }

}
