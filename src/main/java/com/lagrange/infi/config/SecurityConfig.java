package com.lagrange.infi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true) //특정 주소 접근시 권한, 인증을 위한 어노테이션
public class SecurityConfig {
    private static final String[] PERMIT_URL_ARRAY = {
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.httpBasic().disable();
        http.cors().disable();
        http.csrf().disable();

        http.formLogin()
                // custom login page
                .loginPage("/df/login")
                //username parameter 재지정
                .usernameParameter("userid")
                .loginProcessingUrl("/logins/signin")
                .defaultSuccessUrl("/")

                // logout
                .and()
                .logout()
                .logoutSuccessUrl("/df/login")

                .and()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/**").authenticated()
                        // ** config엔 ROLE_이 없어야 하고 DB엔 ROLE_이 접두사로 붙어야함
                        .requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers(PERMIT_URL_ARRAY).permitAll()
                        .anyRequest().permitAll()
                );


        return http.build();
    }



}
