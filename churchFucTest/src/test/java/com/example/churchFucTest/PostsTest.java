package com.example.churchFucTest;

import com.example.churchFucTest.domain.Posts;
import com.example.churchFucTest.repository.PostsRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Posts posts = Posts.builder()
                    .title("Sample..." + i)
                    .author("Sample_author" + i)
                    .content("Sample_content" + i)
                    .build();

            //Create!
            postsRepository.save(posts);
        });
    }


}
