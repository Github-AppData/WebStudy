package com.example.churchFucTest.service;

import com.example.churchFucTest.domain.Posts;
import com.example.churchFucTest.dto.PostsDTO;
import com.example.churchFucTest.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    public Page<PostsDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 5; // 한페이지에 보여줄 글 개수

        // 한 페이지당 3개식 글을 보여주고 정렬 기준은 ID기준으로 내림차순
        /**
         * PageRequest.of
         * @Parameter Name : int pageNumber, int pageSize, Sort.Direction direction, String... properties
         * @Parameter Description
         * 1. pageNumber :
         */
        Page<Posts> postsPages = postsRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC, "id")));

        // 목록 : id, title, content, author
        Page<PostsDTO> postsDTOS = postsPages.map(
                postPage -> new PostsDTO(postPage));

        return postsDTOS;
    }
}
