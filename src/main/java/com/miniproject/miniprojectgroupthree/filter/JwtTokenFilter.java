package com.miniproject.miniprojectgroupthree.filter;

import com.miniproject.miniprojectgroupthree.domain.dto.User;
import com.miniproject.miniprojectgroupthree.service.UserService;
import com.miniproject.miniprojectgroupthree.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final String key;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            log.error("======= Error occurs while get header=========");
            filterChain.doFilter(request, response);
        }

        try {
            log.info("header : {}",header);
            final String token = header.split(" ")[1].trim();
            //TODO: check token is valid
            if (JwtTokenUtils.isExpired(token, key)) {
                log.error("key is expired");
                filterChain.doFilter(request, response);
            }

            //TODO:get userName from token
            String userName = JwtTokenUtils.getUserName(token, key);

            //TODO:check userName is valid
            User user = userService.loadUserByUsername(userName);


            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities()
            );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (RuntimeException e) {
            log.error("Error occurs while validating {}", e.toString());

            filterChain.doFilter(request, response);
        }
    }
}
