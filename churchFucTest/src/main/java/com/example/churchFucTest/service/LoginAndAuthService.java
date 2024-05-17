package com.example.churchFucTest.service;


import com.example.churchFucTest.crypt.PasswordHashingUtil;
import com.example.churchFucTest.domain.User;
import com.example.churchFucTest.dto.SessionUserDTO;
import com.example.churchFucTest.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginAndAuthService {

    private final UserRepository userRepository;

    private final HttpServletRequest request;


    /** 로그인 처리 - 세션에 user 정보 등록 */
    public SessionUserDTO processLogin(String userId, String inputPassword) throws NoSuchAlgorithmException {

        boolean is_exists, isPwValid;
        String salt, hasedPassword, check_pw;

        is_exists = userRepository.existsByUserId(userId);
        User stroedUser = userRepository.findByUserid(userId);

        log.info("stroedUser={}", stroedUser);

        // 로그인 세션 DTO임.
        SessionUserDTO sessionUserDTO = null;

        if(is_exists){ // id가 DB에 있으면,, -> 1단계
            isPwValid = checkPwValid(inputPassword, stroedUser);

            if(isPwValid) { // 만약 비번이 같다면,
                sessionUserDTO = setSessionUserDTO(stroedUser); // 세션 저장.
                stroedUser.set_status(true); // 상태를 로그인 상태로 바꾼다.
                userRepository.save(stroedUser); // 바꾼 상태를 저장.
                return sessionUserDTO;
            } else { // 만약 비번이 같지 않다면,
                log.info("비밀번호가 같지 않습니다.");
                return null;
            }

        } else { // id가 DB에 없으면,,
            log.info("없는 id 입니다.");
            return null;
        }

    }

    private static boolean checkPwValid(String InputPassword, User stroedUser) throws NoSuchAlgorithmException {
        boolean isPwValid;
        String check_pw;
        String hasedPassword;
        check_pw = stroedUser.getPassword();
        hasedPassword = PasswordHashingUtil.hashPassword(InputPassword, stroedUser.getSalt());
        isPwValid = PasswordHashingUtil.verifyPassword(hasedPassword, stroedUser.getPassword());
        return isPwValid;
    }

    private static SessionUserDTO setSessionUserDTO(User stroedUser) {
        SessionUserDTO sessionUserDTO;
        sessionUserDTO = new SessionUserDTO();

        sessionUserDTO.setUserId(stroedUser.getUserId());
        sessionUserDTO.setUsername(stroedUser.getUsername());
        sessionUserDTO.setRoles(stroedUser.getRoles());
        sessionUserDTO.setLoginTime(stroedUser.getLoginTime());
        sessionUserDTO.set_status(stroedUser.is_status());
        sessionUserDTO.setIdx(stroedUser.getIdx());
        return sessionUserDTO;
    }

    /** 회원가입 처리 - 정보저장 및 솔트생성, 비밀번호 암호화 */
    public void processsignUp(User user){

        String password;

        boolean is_exists;
        is_exists = userRepository.existsByUserId(user.getUserId());// id 중복 방지체크

        if (!is_exists) { // 만약 없으면, 회원가입 처리 시작
            String salt = PasswordHashingUtil.generateSalt();
            user.setSalt(salt);

            try {
                // 암호화
                password = PasswordHashingUtil.hashPassword(user.getPassword(), user.getSalt());

                // 해싱된 비밀번호와 솔트를 저장
                user.setPassword(password);

                // 확인 및 저장
                log.info("hashed PW={}", user.getPassword());
                userRepository.save(user);

            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        } else {
            log.info("fail!!!");
        }


    }

    /*에* userid를 인수로 받아, 관리자가 맞는지 체크 */
    public void checkUserOrNotAdmin(String userid){

        // TODO : userid를 받아와서, id와 비밀번호를 check 한다.
        User user = userRepository.findByUserid(userid);




    }

}
