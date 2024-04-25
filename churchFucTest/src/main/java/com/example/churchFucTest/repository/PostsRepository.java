package com.example.churchFucTest.repository;

import com.example.churchFucTest.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
