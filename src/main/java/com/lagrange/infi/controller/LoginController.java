//package com.lagrange.infi.controller;
//
//import com.lagrange.infi.data.dto.Members;
//import com.lagrange.infi.data.model.DefaultRes;
//import com.lagrange.infi.data.server.MemberService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@Slf4j
//@RequestMapping("/logins/")
//public class LoginController {
//
//    MemberService memberService;
//
//    public LoginController(MemberService memberService){
//        this.memberService = memberService;
//    }
//
//    @PostMapping("signup")
//    public ResponseEntity signUp(@RequestBody Members members){
//        try{
//            return new ResponseEntity(memberService.signUp(members),HttpStatus.OK);
//        } catch (Exception e) {
//            log.info(e.getMessage());
//            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES,
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping("signin")
//    public ResponseEntity signin(@RequestBody Members members){
//        try{
//            return new ResponseEntity(memberService.signIn(members),HttpStatus.OK);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES,
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}
