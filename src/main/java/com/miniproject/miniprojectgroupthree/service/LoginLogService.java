package com.miniproject.miniprojectgroupthree.service;

import com.miniproject.miniprojectgroupthree.domain.entity.LoginLog;
import com.miniproject.miniprojectgroupthree.repository.LoginLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginLogService {
    private final LoginLogRepository loginLogRepository;


    public void save(LoginLog loginLog) {
        loginLogRepository.save(loginLog);
    }
}
