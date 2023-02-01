package com.lagrange.infi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String[] PERMIT_URL_ARRAY = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/hello/test"
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
//                .defaultSuccessUrl("/hello/test")
//                .and().logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/hello/test")
//                .invalidateHttpSession(true)
//                .headers().frameOptions().disable()
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
