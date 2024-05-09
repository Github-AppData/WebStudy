package com.example.churchFucTest.service;

import com.example.churchFucTest.domain.SundaySermons;
import com.example.churchFucTest.domain.WednesdaySermons;
import com.example.churchFucTest.domain.YouthSermons;
import com.example.churchFucTest.dto.S_SermonsDTO;
import com.example.churchFucTest.dto.W_SermonsDTO;
import com.example.churchFucTest.dto.Y_SermonsDTO;
import com.example.churchFucTest.repository.S_SermonsRepository;
import com.example.churchFucTest.repository.W_SermonsRepository;
import com.example.churchFucTest.repository.Y_SermonsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostsService{

    //  TODO : 설교 게시판 완성하기 (졸려서 지금 못함)
    //      주일설교, 수요예배 설교, 기타설교 - 버튼 형식으로 게시판이 바뀌는 거 구현하기

    private final S_SermonsRepository sSermonsRepository;
    private final W_SermonsRepository wSermonsRepository;
    private final Y_SermonsRepository ySermonsRepository;

    public Page<?> paging(Pageable pageable, String type) {


        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 5; // 한페이지에 보여줄 글 개수

        // 한 페이지당 3개식 글을 보여주고 정렬 기준은 ID기준으로 내림차순
        /**
         * PageRequest.of
         * @Parameter Name : int pageNumber, int pageSize, Sort.Direction direction, String... properties
         * @Parameter Description
         * 1. pageNumber :
         */
        Page<?> postsPages;

        if(type.equals("Sunday")){
            postsPages = sSermonsRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC, "id")));
        } else if (type.equals("Wednesday")){
            postsPages = wSermonsRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC, "id")));
        } else if (type.equals("Youth")){
            postsPages = ySermonsRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC, "id")));
        } else {
            postsPages = null;
        }

        // 목록 : id, title, content, author
        Page<?> postsDTOS = postsPages.map(
                postPage -> {
                    if (postPage instanceof SundaySermons) {
                        return new S_SermonsDTO((SundaySermons) postPage);
                    } else if (postPage instanceof WednesdaySermons) {
                        return new W_SermonsDTO((WednesdaySermons) postPage);
                    } else if (postPage instanceof YouthSermons){
                        return new Y_SermonsDTO((YouthSermons) postPage);
                    }
                    else {
                        log.info("Error");
                        return null;
                    }
                }

        );


        return postsDTOS;
    }

    public void upView(String type, Long postId){

        if(type.equalsIgnoreCase("Sunday")){
            sSermonsRepository.incrementViews(postId);
            sSermonsRepository.flush();
        } else if (type.equalsIgnoreCase("Wednesday")){
            wSermonsRepository.incrementViews(postId);
            wSermonsRepository.flush();
        } else if (type.equalsIgnoreCase("Youth")){
            ySermonsRepository.incrementViews(postId);
            ySermonsRepository.flush(); // 저장
        } else {
            log.info("Error!!!");
        }

    }
}
