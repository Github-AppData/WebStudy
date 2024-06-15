package com.example.churchFucTest.service;


import com.example.churchFucTest.crypt.PasswordHashingUtil;
import com.example.churchFucTest.domain.User;
import com.example.churchFucTest.dto.SessionUserDTO;
import com.example.churchFucTest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginAndAuthService {

    private final UserRepository userRepository;

    @Autowired
    PasswordHashingUtil passwordHashingUtil;

    /** 로그인 처리 - 세션에 user 정보 등록 */
    public SessionUserDTO processLogin(String userId, String inputPassword) throws NoSuchAlgorithmException {

        boolean is_exists, isPwValid, isPwValidReal;

        // DB에 userid가 있는 지 없는 지 확인하는 boolean 이다.
        is_exists = userRepository.existsByUserId(userId);

        // 저장된 User의 데이터를 DB에서 가지고 온다.
        User stroedUser = userRepository.findByUserid(userId);

        log.info("stroedUser={}", stroedUser);

        // 로그인 세션 DTO임.
        SessionUserDTO sessionUserDTO = null;

        if(is_exists){ // id가 DB에 있으면,, -> 1단계

            // 비밀번호가 input 이랑 저장된 비밀번호랑 맞는지 확인

            String hassedPW = PasswordHashingUtil.hashPassword(inputPassword, stroedUser.getSalt());
            isPwValidReal = PasswordHashingUtil.verifyPassword(hassedPW, stroedUser.getPassword());
            System.out.println("stroedUser.getSalt() = " + stroedUser.getSalt());
            System.out.println();
            System.out.println("stroedUser.getPassword() = " + stroedUser.getPassword());
            System.out.println("hassedPW = " + hassedPW);

            // 0c99fd939dace583fe197e5a93f0f9fd - salt xodnr2024
            if(isPwValidReal) { // 만약 비번이 같다면,
                sessionUserDTO = setSessionUserDTO(stroedUser); // 세션 저장.
                stroedUser.setStatus(true); // 상태를 로그인 상태로 바꾼다.
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

//    private static boolean checkPwValid(String inputPw, User stroedUser,  String salt) {
//        boolean isPwValidReal = false;
//        try {
//
//            return isPwValidReal;
//
//        } catch (NoSuchAlgorithmException e){
//            e.printStackTrace();
//        }
//        return false;
//    }

    private static SessionUserDTO setSessionUserDTO(User stroedUser) {
        SessionUserDTO sessionUserDTO;
        sessionUserDTO = new SessionUserDTO();

        sessionUserDTO.setUserId(stroedUser.getUserId());
        sessionUserDTO.setUsername(stroedUser.getUsername());
        sessionUserDTO.setRoles(stroedUser.getRoles());
        sessionUserDTO.setLoginTime(stroedUser.getLoginTime());
        sessionUserDTO.set_status(stroedUser.isStatus());
        sessionUserDTO.setIdx(stroedUser.getIdx());
        return sessionUserDTO;
    }

    /** 회원가입 처리 - 정보저장 및 솔트생성, 비밀번호 암호화 */
    public void processsignUp(User user){

        String password;

        boolean is_exists;
        System.out.println("processsignUp user = " + user);
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

    public boolean checkUserID(String userid){

        return userRepository.existsByUserId(userid);

    }

}
