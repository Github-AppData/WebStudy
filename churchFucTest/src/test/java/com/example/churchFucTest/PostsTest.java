package com.example.churchFucTest;

import com.example.churchFucTest.domain.SundaySermons;
import com.example.churchFucTest.domain.WednesdaySermons;
import com.example.churchFucTest.domain.YouthSermons;
import com.example.churchFucTest.repository.S_SermonsRepository;
import com.example.churchFucTest.repository.W_SermonsRepository;
import com.example.churchFucTest.repository.Y_SermonsRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class PostsTest {

    private static final Logger log = LoggerFactory.getLogger(S_SermonsRepository.class);

    @Autowired
    S_SermonsRepository sSermonsRepository;

    @Autowired
    W_SermonsRepository wSermonsRepository;

    @Autowired
    Y_SermonsRepository ySermonsRepository;

    @Test
    void test(){
        SundayInsert();
        WednesdayInsert();
        YouthInsert();
//        selectTest();
//        updateTest(1L, "youtube_id", "oHmVSLI7AiU");


    }

    @Transactional
    public void SundayInsert(){
        // 테스트할 SQL Date 생성
        Date testDate = Date.valueOf("2024-04-30");
        LocalDate localDate = LocalDate.now();

        Date date2 = Date.valueOf(localDate);

        IntStream.rangeClosed(1, 100).forEach(i -> {
            SundaySermons sundaySermons = SundaySermons.builder()
                    .title("SundayTitle..." + i)
                    .user_id("Sunday_userId" + i)
                    .content("Sunday_content" + i)
                    .like_num(i)
                    .write_date(date2)
                    .views(i)
                    .youtube_id("m2tBn4XBad0")
                    .build();


            //Create!
            sSermonsRepository.save(sundaySermons);
        });
    }

    @Transactional
    public void WednesdayInsert(){
        // 테스트할 SQL Date 생성
        Date testDate = Date.valueOf("2024-04-30");
        LocalDate localDate = LocalDate.now();

        Date date2 = Date.valueOf(localDate);

        IntStream.rangeClosed(1, 100).forEach(i -> {
            WednesdaySermons wednesdaySermons = WednesdaySermons.builder()
                    .title("WednesdayTitle..." + i)
                    .user_id("Wednesday_userId" + i)
                    .content("Wednesday_content" + i)
                    .like_num(i)
                    .write_date(date2)
                    .views(i)
                    .youtube_id("m2tBn4XBad0")
                    .build();


            //Create!
            wSermonsRepository.save(wednesdaySermons);
            log.info("wednesdaySermons={}",wednesdaySermons);

        });
    }

    @Transactional
    public void YouthInsert(){
        // 테스트할 SQL Date 생성
        Date testDate = Date.valueOf("2024-04-30");
        LocalDate localDate = LocalDate.now();

        Date date2 = Date.valueOf(localDate);

        IntStream.rangeClosed(1, 100).forEach(i -> {
            YouthSermons youthSermons = YouthSermons.builder()
                    .title("YouthTitle..." + i)
                    .user_id("Youth_userid" + i)
                    .content("Youth_content" + i)
                    .like_num(i)
                    .write_date(date2)
                    .views(i)
                    .youtube_id("m2tBn4XBad0")
                    .build();


            //Create!
            ySermonsRepository.save(youthSermons);
            log.info("youthSermons={}",youthSermons);

        });
    }

    @Transactional
    void selectTest(){

        // 데이터를 변형시켜야 한다. Optional로
        // 단축키 : option + Enter -> Add On-demand....
        Optional<SundaySermons> optionalPosts = sSermonsRepository.findById(1L);

        LocalDate localDate = LocalDate.now();
        Date date3 = Date.valueOf(localDate);

        if(optionalPosts.isPresent()){ // 있으면,
            SundaySermons posts =  optionalPosts.get();
            // sSermonsRepository.flush(); // 현재 영속성 컨텍스트에 있는 거 바로 저장.
            log.info("posts={}",posts);

        } else { // 없으면,
            log.info("Retry!!!");
        }

    }

    @Transactional
    void updateTest(Long tranId, String colName, String newPropertyValue){
        Optional<SundaySermons> optionalPosts = sSermonsRepository.findById(1L);

        LocalDate localDate = LocalDate.now();
        Date date3 = Date.valueOf(localDate);

        if(optionalPosts.isPresent()){
            SundaySermons posts = optionalPosts.get();

            log.info("Before posts={}", posts);


            switch (colName){
                case "title":
                    posts.setTitle(newPropertyValue);
                    posts.setWrite_date(date3);
                    sSermonsRepository.save(posts);
                    break;
                case "content":
                    posts.setContent(newPropertyValue);
                    posts.setWrite_date(date3);
                    sSermonsRepository.save(posts);
                    break;
                case "youtube_id":
                    posts.setYoutube_id(newPropertyValue);
                    posts.setWrite_date(date3);
                    sSermonsRepository.save(posts);
                    break;
            }

            log.info("After posts={}", posts);

        }
    }
}
