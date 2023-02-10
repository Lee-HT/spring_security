package com.lagrange.infi.controller;

import com.lagrange.infi.config.auth.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/")
public class SecurityRoleController {

    @GetMapping("user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails){
        log.info("principalDetails : "+principalDetails.getMemberE());
        return "user";
    }

    @GetMapping("admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("manager")
    public @ResponseBody String manager(){
        return "manager";
    }

}
