package com.dave.springboot;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String greet() {
        return "Hello Spring Boot";
    }
    @RequestMapping("/study")
    public String study () {
        System.out.println("Telusko is here!");
        return "Telusko doesn't teach, he educate";
    }

}
