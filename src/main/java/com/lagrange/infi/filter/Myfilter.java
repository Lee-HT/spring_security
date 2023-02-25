package com.lagrange.infi.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Myfilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("필터1_requestURL : " + request.getRequestURL());

//        request.setCharacterEncoding("UTF-8");

        if(request.getMethod().equals("POST")){
            log.info(request.getMethod());
            String headerAuth = request.getHeader("Authorization");
            log.info(headerAuth);

            if(headerAuth.equals("lia")){
                filterChain.doFilter(request,response);
            }else{
                PrintWriter outPw = response.getWriter();
                outPw.println("Authorization != lia");
                filterChain.doFilter(request,response);
            }
        }else{
            filterChain.doFilter(request,response);
        }
    }
}
