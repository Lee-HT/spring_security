package com.lagrange.infi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class SecurityRoleController {

    @GetMapping("user")
    public @ResponseBody String user(){ return "user"; }

    @GetMapping("admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("manager")
    public @ResponseBody String manager(){
        return "manager";
    }

}
