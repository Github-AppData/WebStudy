package com.example.churchFucTest.repository;

import com.example.churchFucTest.domain.WednesdaySermons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface W_SermonsRepository extends JpaRepository<WednesdaySermons, Long> {

    
}
