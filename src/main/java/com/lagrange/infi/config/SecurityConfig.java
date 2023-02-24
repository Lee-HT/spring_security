package com.lagrange.infi.config;


import com.lagrange.infi.config.jwt.JwtAuthenticationFilter;
import com.lagrange.infi.config.jwt.JwtAuthorizationFilter;
import com.lagrange.infi.data.repository.MemberRepository;
import com.lagrange.infi.filter.Myfilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true) //특정 주소 접근시 권한, 인증을 위한 어노테이션
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private final CorsConfig corsConfig;
    @Autowired
    private final MemberRepository memberRepository;


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
//        http.cors().disable();
        http.csrf().disable();

        http.addFilterBefore(new Myfilter(), SecurityContextPersistenceFilter.class);

        //security 세션 생성 x 기존 것도 사용 x
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilter(corsConfig.corsFilter()); //@CrossOrigin(인증 x) , security 필터에 등록 인증

        http.apply(new MyCustomDsl());

        // authorize
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/**").authenticated()
                        // ** config엔 ROLE_이 없어야 하도록 변경됨 ** DB엔 ROLE_이 붙어야함
                        .requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers(PERMIT_URL_ARRAY).permitAll()
                        .anyRequest().permitAll());

        // formlogin
//        http.formLogin().disable();

//        http.formLogin()
//                // custom login page
//                .loginPage("/login/login")
//                //username parameter 재지정
//                .usernameParameter("userid")
//                .loginProcessingUrl("/logins/signin")
//                .defaultSuccessUrl("/user")
//
//                // logout
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login/login");
//
//        // oauth2
//        http.oauth2Login()
//                .loginPage("/login/login")
//                .defaultSuccessUrl("/logins/login")
//                .failureUrl("/login/login");

//                .userInfoEndpoint()
//                .userService(principalOauth2UserService);

        return http.build();
    }

    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl,HttpSecurity>{
        @Override
        public void configure(HttpSecurity http) throws Exception{
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http.addFilter(corsConfig.corsFilter());
            http.addFilter(new JwtAuthenticationFilter(authenticationManager));
            http.addFilter(new JwtAuthorizationFilter(authenticationManager,memberRepository));
        }
    }
}
