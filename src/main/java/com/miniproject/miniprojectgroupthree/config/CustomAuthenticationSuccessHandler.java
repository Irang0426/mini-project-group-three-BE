package com.miniproject.miniprojectgroupthree.config;

import com.miniproject.miniprojectgroupthree.domain.entity.LoginLog;
import com.miniproject.miniprojectgroupthree.service.LoginLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final LoginLogService logService; // 로그 서비스 클래스


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String username = authentication.getName(); // 로그인한 사용자 이름
        String userAgent = request.getHeader("User-Agent"); // 로그인한 사용자 브라우저 정보
        String clientIp = getClientIp(request); // 로그인한 사용자 IP 주소
        Timestamp loginTime = new Timestamp(new Date().getTime()); // 로그인 시간

        // 로그 정보 저장
        LoginLog loginLog = new LoginLog(username, userAgent, clientIp, loginTime);
        logService.save(loginLog);

        // 로그인 성공 후 이동할 URL 설정
        String targetUrl = determineTargetUrl(authentication);

        response.sendRedirect(targetUrl);
    }

    // 클라이언트 IP 주소 가져오기
    private String getClientIp(HttpServletRequest request) {
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }

    // 로그인 성공 후 이동할 URL 설정
    private String determineTargetUrl(Authentication authentication) {
        // TODO: 로그인 성공 후 이동할 URL 설정 로직 추가
        return "/";
    }
}
