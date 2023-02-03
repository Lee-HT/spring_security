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
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .httpBasic().disable()
                .cors().disable()
                .csrf().disable()

                .formLogin()
                .loginPage("/df/login")
                .usernameParameter("userid")
                .loginProcessingUrl("/logins/signin")
                .defaultSuccessUrl("/")

                .and()
                .authorizeHttpRequests()
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/manager/**").hasRole("ADMIN or MANAGER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers(PERMIT_URL_ARRAY).permitAll()
                .anyRequest().permitAll();
        return httpSecurity.build();
    }



}
