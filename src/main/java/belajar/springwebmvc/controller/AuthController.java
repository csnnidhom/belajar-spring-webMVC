package belajar.springwebmvc.controller;

import belajar.springwebmvc.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @PostMapping(value = "/auth/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> login(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ){
        if ("Nidhom".equals(username) && "rahasia".equals(password)){
            HttpServletRequest session = (HttpServletRequest) httpServletRequest.getSession(true);
            session.setAttribute("user", new User(username));


            Cookie cookie = new Cookie("username", username);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("KO");
        }
    }

    @GetMapping(path = "/auth/user")
    public ResponseEntity<String> getuser(@CookieValue("username") String username){
        return ResponseEntity.ok("Hello " + username);
    }

}
