package com.lagrange.infi.controller;

import com.lagrange.infi.config.auth.PrincipalDetails;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/")
@Tag(name="Role view",description = "")
public class SecurityRoleController {

    //jwt filter용 user
    @GetMapping("user")
    public @ResponseBody String user(Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        log.info("authentication : " + principalDetails.getUsername());
        return "user";
    }

    //session용 user
//    @GetMapping("user")
//    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails){
//        log.info("user principalDetails : "+principalDetails.getMemberE());
//        return "user";
//    }

    @GetMapping("admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("manager")
    public @ResponseBody String manager(){
        return "manager";
    }

}
