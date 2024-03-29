package site.metacoding.blogv2.config.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.blogv2.web.api.dto.user.LoginDto;

public class MyFilter2 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("MyFilter 2");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        LoginDto loginDto = null;

        if (req.getMethod().equals("POST")) { // 대소문자 구분 잘하기
            String contentType = req.getHeader("Content-Type");
            System.out.println(contentType);

            if (contentType.equals("application/x-www-form-urlencoded")) {
                String username = req.getParameter("username"); // 파싱 - 스프링 기본전략
                String password = req.getParameter("password"); // 파싱
                loginDto = new LoginDto();
                loginDto.setUsername(username);
                loginDto.setPassword(password);

                System.out.println("파싱 잘됨~~~~~~~~~");
                System.out.println(loginDto);
            } else if (contentType.equals("application/json")) { // json은 버퍼로 읽어야 함
                BufferedReader br = req.getReader();
                String body = "";
                while (true) {
                    String input = br.readLine();

                    if (input == null) {
                        break;
                    }
                    body = body + input;
                }

                System.out.println("===============================");
                System.out.println(body); // username=ssar&password=1234

                ObjectMapper om = new ObjectMapper();
                loginDto = om.readValue(body, LoginDto.class); // gson.fromJson();
                System.out.println(loginDto);
            }
        } else {
            System.out.println("POST 요청이 아닙니다.");
        }

        if (loginDto.getPassword() == null || loginDto.getPassword().equals("")) {
            resp.setContentType("text/plain; charset=utf-8"); // 설정 안하면 브라우저에서 글자 깨짐
            PrintWriter out = resp.getWriter();
            out.println("비밀번호 없어서 못 들어옴");
            out.flush();
            System.out.println(out);
        } else {
            chain.doFilter(request, response);
        }

    }

}