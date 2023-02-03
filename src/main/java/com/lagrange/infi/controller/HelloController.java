package com.lagrange.infi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/df/")
@Tag(name = "Hello Controller",description = "테스트용")
public class HelloController
{
    @GetMapping("test")
    @Operation(summary = "hello.mustache",description = "view")
    public String hello(){
        return "hello";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("register")
    public String register(){
        return "register";
    }

}
