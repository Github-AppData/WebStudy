package com.example.churchFucTest.repository;

import com.example.churchFucTest.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    /** userid를 사용하여 사용자 정보를 가져옴 */
    @Transactional
    @Query(value = "select * from User where user_Id = :userId", nativeQuery = true)
    public User findByUserid(@Param("userId") String userId);


    /** 인수로 받은 userid가 DB에 있는 지 체크 */
    @Transactional(readOnly = true)
    public boolean existsByUserId(String userId);


}
