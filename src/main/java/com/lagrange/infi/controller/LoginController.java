package com.lagrange.infi.controller;

import com.lagrange.infi.data.dto.MemberD;
import com.lagrange.infi.data.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/logins/")
@Tag(name = "Member 관리",description = "")
public class LoginController {

    private MemberService memberService;

    @Autowired
    public LoginController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("signup")
    @Operation(summary = "회원가입",description = "")
    public String register(String id, String password, String email){
        try{
            log.info(ResponseEntity.status(HttpStatus.OK)
                    .body(memberService.register(id,password,email)).toString());
            return "redirect:/df/registry";
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MemberD(0L,id,password,email)).toString());
            return "redirect:/df/register";
        }
    }

    @PostMapping("update")
    @Operation(summary = "회원 정보 수정",description = "")
    public String update(@Valid @RequestBody MemberD memberD){
        try{
            log.info(ResponseEntity.status(HttpStatus.OK)
                    .body(memberService.update(memberD.getIdx(),memberD.getId(),memberD.getPassword(),
                            memberD.getEmail())).toString());
            return "redirect:/df/login";
        }catch (Exception e) {
            log.info(e.getMessage());
            log.info(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(memberD).toString());
            return "redirect:/df/registry";
        }
    }

    @PostMapping("signin")
    @Operation(summary = "로그인",description = "")
    public String login(String id,String password){
        try{
            log.info(ResponseEntity.status(HttpStatus.OK).body(memberService.login(id,password)).toString());
            return "redirect:/df/login";
        } catch (Exception e) {
            log.error(e.getMessage());
            log.info(new ResponseEntity("fail",
                    HttpStatus.INTERNAL_SERVER_ERROR).toString());
            return "redirect:/df/login";
        }
    }

    @PostMapping("test")
    @Operation(summary = "repository",description = "테스트용")
    public ResponseEntity test(Long idx){
        return new ResponseEntity(memberService.getidx(idx),HttpStatus.OK);
    }

}
