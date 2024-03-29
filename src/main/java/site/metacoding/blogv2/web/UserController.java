package site.metacoding.blogv2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

// Controller 는 파일 리턴 전용 컨트롤러이다.
// 브라우저를 위한 컨트롤러 (html 파일로 페이지 주는 컨트롤러)

@RequiredArgsConstructor
@Controller
public class UserController {

    // private final UserService userService;
    // private final HttpSession session;

    // 앱은 이 친구 요청 할까? 아니!!
    // 웹은 이 친구 요청해야함.
    @GetMapping("/s/user/{id}")
    public String userInfo(@PathVariable Integer id /* , Model model */) {
        // User userEntity = userService.회원정보(id);
        // model.addAttribute("user", userEntity);
        return "user/updateForm";
    }

    // 웹브라우저 -> 회원가입 페이지 주세요!!
    // 앱 -> 회원가입 페이지 주세요!! 말이 안됨!!
    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {

        // 쿠키 로직 (이제 이렇게 안 쓰고 자바스크립트로 처리)
        // String cookieValue = request.getHeader("Cookie");
        // System.out.println(cookieValue);

        return "user/loginForm";
    }

    // 로그아웃
    // @GetMapping("/logout")
    // public String logout() {
    // session.invalidate(); // 세션 무효화 (세션 아이디 영역의 데이터를 다 삭제해)
    // return "redirect:/";
    // }
}
