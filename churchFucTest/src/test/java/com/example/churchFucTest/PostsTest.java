package com.example.churchFucTest;

import com.example.churchFucTest.domain.Posts;
import com.example.churchFucTest.repository.PostsRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootTest
public class PostsTest {

    private static final Logger log = LoggerFactory.getLogger(PostsRepository.class);

    @Autowired
    PostsRepository postsRepository;

    @Test
    void test(){
        InsertPosts();

    }

    @Transactional
    public void InsertPosts(){
        // 테스트할 SQL Date 생성
        Date testDate = Date.valueOf("2024-04-30");


        IntStream.rangeClosed(1, 100).forEach(i -> {
            Posts posts = Posts.builder()
                    .title("Sample..." + i)
                    .user_id("Sample_userId" + i)
                    .content("Sample_content" + i)
                    .like_num(i)
                    .write_date(testDate)
                    .views(i)
                    .build();

            //Create!
            postsRepository.save(posts);
        });
    }


}
