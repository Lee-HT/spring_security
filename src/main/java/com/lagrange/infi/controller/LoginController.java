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
    @Operation(summary = "회원가입",description = "userid. password, email")
    public String register(String userid, String password, String email){
        try{
            log.info(ResponseEntity.status(HttpStatus.OK)
                    .body(memberService.register(userid,password,email)).toString());
            return "redirect:/login/login";
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MemberD(0L,userid,password,email)).toString());
            return "redirect:/login/register";
        }
    }

    @PostMapping("update")
    @Operation(summary = "회원 정보 수정",description = "id, userid, password, email")
    public String update(@Valid @RequestBody MemberD memberD){
        try{
            log.info(ResponseEntity.status(HttpStatus.OK)
                    .body(memberService.update(memberD.getId(),memberD.getUserid(),memberD.getPassword(),
                            memberD.getEmail())).toString());
            return "redirect:/login/login";
        }catch (Exception e) {
            log.info(e.getMessage());
            log.info(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(memberD).toString());
            return "redirect:/login/registry";
        }
    }

    @PostMapping("signin")
    @Operation(summary = "로그인",description = "userid, password")
    public String login(String userid,String password){
        try{
            log.info(ResponseEntity.status(HttpStatus.OK)
                    .body(memberService.login(userid,password)).toString());
            return "redirect:/df/login";
        } catch (Exception e) {
            log.error(e.getMessage());
            log.info(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("fail").toString());
            return "redirect:/login/login";
        }
    }

    @PostMapping("unregister")
    @Operation(summary = "회원탈퇴",description = "userid, password")
    public String unregister(String userid,String password){
        try{
            log.info(ResponseEntity.status(HttpStatus.OK)
                    .body(memberService.unregister(userid,password)).toString());
            return "redirect:/login/login";
        } catch (Exception e) {
            log.error(e.getMessage());
            log.info(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail").toString());
            return "redirect:/";
        }
    }

    @PostMapping("test")
    @Operation(summary = "repository",description = "테스트용")
    public ResponseEntity test(Long id){
        return new ResponseEntity(memberService.getId(id),HttpStatus.OK);
    }

}
