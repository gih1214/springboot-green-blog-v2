package site.metacoding.blogv2.web.api;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.user.User;
import site.metacoding.blogv2.service.UserService;
import site.metacoding.blogv2.web.api.dto.ResponseDto;
import site.metacoding.blogv2.web.api.dto.user.JoinDto;
import site.metacoding.blogv2.web.api.dto.user.LoginDto;

// ApiController 는 제이슨 리턴 전용 컨트롤러이다.

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final HttpSession session;

    // 회원가입
    @PostMapping("/api/join")
    public ResponseDto<String> join(@RequestBody JoinDto joinDto) {
        // System.out.println(joinDto);
        userService.회원가입(joinDto);
        return new ResponseDto<String>(1, "회원가입성공", null);
    }

    // 로그인
    @PostMapping("/api/login")
    public ResponseDto<String> login(@RequestBody LoginDto loginDto) {
        User userEntity = userService.로그인(loginDto);

        if (userEntity == null) {
            return new ResponseDto<String>(-1, "로그인실패", null);
        }

        session.setAttribute("principal", userEntity);
        return new ResponseDto<String>(1, "로그인성공", null);
    }
}
