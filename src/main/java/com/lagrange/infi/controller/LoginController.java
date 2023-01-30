package com.lagrange.infi.controller;

import com.lagrange.infi.data.dto.Members;
import com.lagrange.infi.data.service.MemberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/logins/")
public class LoginController {

    MemberService memberService;

    public LoginController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("signup")
    public ResponseEntity<Members> register(@Valid @RequestBody Members members){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(memberService.register(members.getId(),members.getPassword()));
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(members);
        }
    }

    @PostMapping("signin")
    public ResponseEntity login(@RequestBody String id,String password){
        try{
            return new ResponseEntity(memberService.login(id,password),HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity("fail",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
