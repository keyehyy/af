package com.gt.af.s.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping("/login")
    public String loginPage(){
        System.out.println("login page");
        return "login";
    }
    @GetMapping("/index")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String index(){
        System.out.println("index page");
        return "index";
    }


    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String printAdmin(){
        System.out.println("hello admin");
        return "admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String printUser(){
        System.out.println("hello user");
        return "user";
    }

    /**
     * 找不到页面
     */
    @GetMapping("/404")
    public String notFoundPage() {
        return "/error/404";
    }
    /**
     * 未授权
     */
    @GetMapping("/403")
    public String accessError() {
        return "/error/403";
    }
    /**
     * 服务器错误
     */
    @GetMapping("/500")
    public String internalError() {
        return "/error/500";
    }
}