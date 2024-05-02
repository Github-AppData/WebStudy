package com.example.churchFucTest.controller;

import com.example.churchFucTest.dto.PostsDTO;
import com.example.churchFucTest.repository.PostsRepository;
import com.example.churchFucTest.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sermons/board")
public class SermonsController {

    private final PostsService postsService;
    private final PostsRepository postsRepository;

    @GetMapping
    public String sermons(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<PostsDTO> postsPages = postsService.paging(pageable);

        /**
         * blockLimit : page 개수 설정
         * 현재 사용자가 선택한 페이지 앞 뒤로 blockLimit 갯수만큼의 페이지씩만 보여준다.
         * ex : 현재 사용자가 설정한 blockLimit가 4페이지라면 2, 3, (4), 5, 6
         */
        int blockLimit = 5;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), postsPages.getTotalPages());

        model.addAttribute("postsPages", postsPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "sermons/board";
    }

    @GetMapping("/{postId}")
    public String getPost(@PathVariable("postId") int postId, Model model) {
//        postsRepository.findAllById(postId);
        // postId에 해당하는 게시물을 가져오거나 작업을 수행한다.
        // 이후 해당하는 페이지를 반환한다.
        // 여기서는 페이지 이름을 반환하며, Thymeleaf 템플릿 엔진은 해당 페이지를 렌더링한다.
        model.addAttribute("postId", postId); // 모델에 postId를 추가하여 뷰에서 사용할 수 있도록 한다.
        log.info("postId={}", postId);
        return "sermons/detail"; // postPage.html과 같은 Thymeleaf 템플릿 파일을 반환한다.
    }




}
