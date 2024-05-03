package com.example.churchFucTest.repository;

import com.example.churchFucTest.domain.SundaySermons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S_SermonsRepository extends JpaRepository<SundaySermons, Long> {

    
}
