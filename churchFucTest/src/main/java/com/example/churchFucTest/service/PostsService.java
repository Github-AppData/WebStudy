package com.example.churchFucTest.service;

import com.example.churchFucTest.domain.SundaySermons;
import com.example.churchFucTest.dto.S_SermonsDTO;
import com.example.churchFucTest.repository.S_SermonsRepository;
import com.example.churchFucTest.repository.W_SermonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsService{

    //  TODO : 설교 게시판 완성하기 (졸려서 지금 못함)
    //      주일설교, 수요예배 설교, 기타설교 - 버튼 형식으로 게시판이 바뀌는 거 구현하기

    private final S_SermonsRepository sSermonsRepository;
    private final W_SermonsRepository wSermonsRepository;

    public Page<S_SermonsDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 5; // 한페이지에 보여줄 글 개수

        // 한 페이지당 3개식 글을 보여주고 정렬 기준은 ID기준으로 내림차순
        /**
         * PageRequest.of
         * @Parameter Name : int pageNumber, int pageSize, Sort.Direction direction, String... properties
         * @Parameter Description
         * 1. pageNumber :
         */
        Page<SundaySermons> postsPages = sSermonsRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC, "id")));

        // 목록 : id, title, conten   t, author
        Page<S_SermonsDTO> postsDTOS = postsPages.map(
                postPage -> new S_SermonsDTO(postPage));

        return postsDTOS;
    }
}
