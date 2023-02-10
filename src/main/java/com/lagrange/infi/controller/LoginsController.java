package com.lagrange.infi.controller;

import com.lagrange.infi.config.auth.PrincipalDetails;
import com.lagrange.infi.data.dto.MemberD;
import com.lagrange.infi.data.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Slf4j
@RequestMapping("/logins/")
@Tag(name = "Member 관리",description = "")
public class LoginsController {

    private MemberService memberService;

    @Autowired
    public LoginsController(MemberService memberService){
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


    @GetMapping("login")
    public @ResponseBody String login(Authentication authentication,
            @AuthenticationPrincipal PrincipalDetails userDetails){
        log.info("login authentication ----------");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        log.info("principal : " + principalDetails.getMemberE());
        log.info("userDetails : " + userDetails.getMemberE());
        return "userDetails 세션 정보 확인하기";
    }

    @GetMapping("google")
    public @ResponseBody String googlesignin(Authentication authentication,
            @AuthenticationPrincipal OAuth2User oAuth2Users){
        log.info("google authentication ----------");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        log.info("principal : " + principalDetails.getAttributes());
        log.info("oauth2User : " + oAuth2Users.getAttributes());
        return "OAuth 세션 정보 확인하기";

    }

    @PostMapping("test")
    @Operation(summary = "repository",description = "테스트용")
    public ResponseEntity test(Long id){
        return new ResponseEntity(memberService.getId(id),HttpStatus.OK);
    }

}
