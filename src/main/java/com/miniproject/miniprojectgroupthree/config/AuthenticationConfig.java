package com.miniproject.miniprojectgroupthree.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class AuthenticationConfig {//spring security 설정

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("=============== confugure spring security filter chain==================");

        http.csrf().disable();
        http.httpBasic().disable();

        return http.sessionManagement()
                   .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
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
