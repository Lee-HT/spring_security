package com.lagrange.infi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/hello/")
@Tag(name = "Hello",description = "Controller")
public class HelloController
{
    @GetMapping("test")
    @Operation(summary = "get 테스트",description = "Operation description")
    public String hello(){

        return "hello";
    }
}
