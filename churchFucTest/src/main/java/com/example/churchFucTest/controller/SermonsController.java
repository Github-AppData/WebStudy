package com.example.churchFucTest.controller;

import com.example.churchFucTest.domain.SundaySermons;
import com.example.churchFucTest.domain.WednesdaySermons;
import com.example.churchFucTest.domain.YouthSermons;
import com.example.churchFucTest.repository.S_SermonsRepository;
import com.example.churchFucTest.repository.W_SermonsRepository;
import com.example.churchFucTest.repository.Y_SermonsRepository;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sermons/board")
public class SermonsController {

    private final PostsService postsService;
    private final S_SermonsRepository sSermonsRepository;
    private final W_SermonsRepository wSermonsRepository;
    private final Y_SermonsRepository ySermonsRepository;


    @GetMapping("/sunday")
    public String sermons(@PageableDefault(page = 1) Pageable pageable, Model model, RedirectAttributes redirectAttributes) {
        Page<?> postsPages = postsService.paging(pageable, "Sunday");

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
        model.addAttribute("type", "sunday");
        model.addAttribute("day", "주일예배");
        log.info("type={}", "sunday");


        return "sermons/board";
    }


    // 수요설교
    @GetMapping("/wednesday")
    public String wednesday_sermons(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<?> postsPages = postsService.paging(pageable, "Wednesday");

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
        model.addAttribute("type", "wednesday");
        model.addAttribute("day", "수요예배");

        log.info("type={}", "wednesday");

        return "sermons/board";

    }

    @GetMapping("/youth")
    public String youth_sermons(@PageableDefault(page = 1) Pageable pageable, Model model, RedirectAttributes redirectAttributes) {
        Page<?> postsPages = postsService.paging(pageable, "Youth");

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

        model.addAttribute("type", "youth");
        model.addAttribute("day", "청년회 예배");

        log.info("type={}", "youth");


//        redirectAttributes.addAttribute("page", 1);
        return "sermons/board";
    }

    // 삭제
    @PostMapping("/{type}/{postId}/delete")
    public String deletePost(){
        // TODO : 관리자가 맞는지 로그인 세션인증 구현해야 한다.


        return "sermons/detail";
    }

    // 수정
    @PostMapping("/{type}/{postId}")
    public String revisePost(){
        // TODO : 관리자가 맞는 지 로그인 세션인증 구현해야 한다.

        return "sermons/detail";
    }



    @GetMapping("/{type}/{postId}")
    public String getPost(@PathVariable("postId") Long postId, @PathVariable("type") String type, Model model) {
        // postId에 해당하는 게시물을 가져오거나 작업을 수행한다.
        // 이후 해당하는 페이지를 반환한다.
        // 여기서는 페이지 이름을 반환하며, Thymeleaf 템플릿 엔진은 해당 페이지를 렌더링한다.

        // 타입에 따라 다른 Repository를 선택하여 데이터를 가져온다.

        // TODO : Service로 뺴야 함.
        Object post;
        if ("sunday".equalsIgnoreCase(type)) {
            Optional<SundaySermons> optionalPost = sSermonsRepository.findById(postId);
            post = optionalPost.orElse(null);
        } else if ("wednesday".equalsIgnoreCase(type)) {
            Optional<WednesdaySermons> optionalPost = wSermonsRepository.findById(postId);
            post = optionalPost.orElse(null);
        } else if ("youth".equalsIgnoreCase(type)) {
            Optional<YouthSermons> optionalPost = ySermonsRepository.findById(postId);
            post = optionalPost.orElse(null);
        } else {
            // 지정된 타입이 아닌 경우 처리할 내용
            post = null;
        }

        if (post != null) {
            log.info("Post found: {}", post);
            model.addAttribute("sermon", post);
        } else {
            log.info("Post not found for postId: {}", postId);
            // postId에 해당하는 게시물이 없을 때 처리할 내용
        }

        postsService.upView(type, postId);

        return "sermons/detail"; // sermons/detail.html과 같은 Thymeleaf 템플릿 파일을 반환한다.
    }







}
