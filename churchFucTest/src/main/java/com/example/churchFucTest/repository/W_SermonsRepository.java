package com.example.churchFucTest.repository;

import com.example.churchFucTest.domain.WednesdaySermons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface W_SermonsRepository extends JpaRepository<WednesdaySermons, Long> {

    @Transactional
    @Modifying
    @Query(value = "update wednesday_sermons set views = views + 1 where id = :postId", nativeQuery = true)
    void incrementViews(@Param("postId") Long postId);

}
