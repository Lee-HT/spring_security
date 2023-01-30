package com.lagrange.infi.controller;

import com.lagrange.infi.data.dto.MemberD;
import com.lagrange.infi.data.entity.MemberE;
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
    public ResponseEntity<MemberD> register(@Valid @RequestBody MemberD memberD){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(memberService.register(memberD.getId(),memberD.getPassword()));
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(memberD);
        }
    }

    @PostMapping("update")
    public ResponseEntity<MemberD> update(@Valid @RequestBody MemberD memberS){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(memberService.update(memberS.getIdx(),memberS.getId(),memberS.getPassword()));
        }catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(memberS);
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
