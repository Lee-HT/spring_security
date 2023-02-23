package com.lagrange.infi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping(value = "/login/")
@Tag(name = "LoginView Controller", description = "")
public class LoginViewController {

    @GetMapping("test")
    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @Operation(summary = "hello.mustache", description = "view")
    public String hello() {
        return "hello";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @GetMapping("home")
    public @ResponseBody String home() {
        return "<h1>home</h1>";
    }

    @PostMapping("token")
    public @ResponseBody String token() {
        return "<h1>home<h1>";
    }

}
