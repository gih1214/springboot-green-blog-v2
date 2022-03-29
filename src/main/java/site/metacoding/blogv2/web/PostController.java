package site.metacoding.blogv2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.service.PostService;

// Controller 는 파일 리턴 전용 컨트롤러이다.
// 브라우저를 위한 컨트롤러 (html 파일로 페이지 주는 컨트롤러)

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    // 페이지를 줘
    // /s 붙었으니까 자동으로 인터셉터가 인증 체크함.
    @GetMapping("/s/post/writeForm")
    public String writeForm() {
        return "post/writeForm";
    }

    @GetMapping({ "/", "/post" }) // post 테이블에 있는 애들 다 주세요
    public String home() {
        return "post/list";
    }
}
