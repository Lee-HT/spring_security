package com.lagrange.infi.config;

import com.lagrange.infi.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Autowired
//    private PrincipalOauth2UserService principalOauth2UserService;

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
                .loginPage("/login/login")
                //username parameter 재지정
                .usernameParameter("userid")
                .loginProcessingUrl("/logins/signin")
                .defaultSuccessUrl("/")

                // logout
                .and()
                .logout()
                .logoutSuccessUrl("/login/login")

                .and()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/**").authenticated()
                        // ** config엔 ROLE_이 없어야 하고 DB엔 ROLE_이 접두사로 붙어야함
                        .requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers(PERMIT_URL_ARRAY).permitAll()
                        .anyRequest().permitAll()
                );

        http.oauth2Login()
                .loginPage("/login/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login/login");
//                .userInfoEndpoint()
//                .userService(principalOauth2UserService);


        return http.build();
    }



}
