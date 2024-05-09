package com.example.churchFucTest.repository;

import com.example.churchFucTest.domain.SundaySermons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface S_SermonsRepository extends JpaRepository<SundaySermons, Long> {

    @Transactional
    @Modifying
    @Query(value = "update sunday_sermons set views = views + 1 where id = :postId", nativeQuery = true)
    void incrementViews(@Param("postId") Long postId);

//    @Transactional
//    @Modifying
//    @Query(value = "update sunday_sermons set views = views + 1 where id = :postId", nativeQuery = true)
//    void (@Param("postId") Long postId);


}
