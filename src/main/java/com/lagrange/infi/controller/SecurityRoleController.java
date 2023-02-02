package com.lagrange.infi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SecurityRoleController {

    @GetMapping("user")
    public String user(){
        return "user";
    }

    @GetMapping("admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("manager")
    public String manager(){
        return "manager";
    }

}
