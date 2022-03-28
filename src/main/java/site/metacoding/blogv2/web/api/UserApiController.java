package site.metacoding.blogv2.web.api;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.user.User;
import site.metacoding.blogv2.service.UserService;
import site.metacoding.blogv2.web.api.dto.ResponseDto;
import site.metacoding.blogv2.web.api.dto.user.JoinDto;
import site.metacoding.blogv2.web.api.dto.user.LoginDto;
import site.metacoding.blogv2.web.api.dto.user.UpdateDto;

// ApiController 는 제이슨 리턴 전용 컨트롤러이다.

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final HttpSession session;

    // password, email, addr
    @PutMapping("/s/api/user/{id}")
    public ResponseDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {

        // 세션의 아이디와 {id}를 비교
        User principal = (User) session.getAttribute("principal");
        if (principal.getId() != id) {
            throw new RuntimeException("권한이 없습니다.");
        }

        User userEntity = userService.회원수정(id, updateDto);
        session.setAttribute("principal", userEntity); // 세션 변경하기
        return new ResponseDto<>(1, "성공", null);
    }

    // 우리는 호출할 일이 없다.
    // 웹브라우저에서는 현재 사용 안함. 추후 앱에서 요청시에 사용할 예정
    // 이것도 있어야 나중에 앱에서 상세보기 할 수 있음.
    @GetMapping("/s/api/user/{id}")
    public ResponseDto<?> userInfo(@PathVariable Integer id) {
        User userEntity = userService.회원정보(id);
        return new ResponseDto<>(1, "성공", userEntity);
    }

    // 회원가입
    @PostMapping("/join")
    public ResponseDto<?> join(@RequestBody JoinDto joinDto) {
        // System.out.println(joinDto);
        userService.회원가입(joinDto);
        return new ResponseDto<>(1, "회원가입성공", null);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        User userEntity = userService.로그인(loginDto);

        if (userEntity == null) {
            return new ResponseDto<>(-1, "로그인실패", null);
        }

        // 쿠키 로직
        if (loginDto.getRemember().equals("on")) {
            response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() + "; path=/");
        }
        // response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() + ";
        // path=/; httpOnly=true");
        // response.addHeader("Set-Cookie", "path/=");

        // addHeader 대신 addCookie 사용 가능
        // Cookie cookie = new Cookie("remember", loginDto.getUsername());
        // cookie.setPath("/");
        // response.addCookie(cookie);

        session.setAttribute("principal", userEntity);
        return new ResponseDto<>(1, "로그인성공", null);
    }

    // 회원가입 페이지주세요, 회원가입할게요, 로그인페이지주세요, 로그인할게요
    // 로그아웃할게요 -> 인증주소 안 붙여도 됨. /s
    @GetMapping("/logout")
    public ResponseDto<?> logout() {
        session.invalidate();
        return new ResponseDto<>(1, "성공", null);
    }

}
