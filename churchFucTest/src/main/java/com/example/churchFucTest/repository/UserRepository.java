package com.example.churchFucTest.repository;

import com.example.churchFucTest.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Query(value = "select * from User where user_Id = :userId", nativeQuery = true)
    public User findByUserId(@Param("userId") String userId);


    @Transactional(readOnly = true)
    public boolean existsByUserId(String userId);

}
