package com.miniproject.miniprojectgroupthree.config;

import com.miniproject.miniprojectgroupthree.filter.JwtTokenFilter;
import com.miniproject.miniprojectgroupthree.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class AuthenticationConfig {//spring security 설정

    private final UserService userService;
    private final CustomAuthenticationSuccessHandler successHandler;
    @Value("${jwt.token.secret-key}")
    private String key;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("=============== confugure spring security filter chain==================");

        http.csrf().disable();
        http.httpBasic().disable();

        return http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
//                .loginPage("/")
                .successHandler(successHandler)
                .and()
                //error 발생시 entrypoint
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationConfig())
                .and()
                .addFilterBefore(new JwtTokenFilter(key, userService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    protected WebSecurityCustomizer webSecurityCustomizer() {
        log.info("============= my configure=============");

        return web -> {
            web.ignoring().requestMatchers(PathRequest.toStaticResources()
                            .atCommonLocations())
                    .antMatchers("/api/*/users/join", "/api/*/users/login");
        };
    }

}
