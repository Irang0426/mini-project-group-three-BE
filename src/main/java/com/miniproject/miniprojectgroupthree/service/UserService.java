package com.miniproject.miniprojectgroupthree.service;

import com.miniproject.miniprojectgroupthree.domain.dto.User;
import com.miniproject.miniprojectgroupthree.domain.entity.UserEntity;
import com.miniproject.miniprojectgroupthree.exception.AppException;
import com.miniproject.miniprojectgroupthree.exception.ErrorCode;
import com.miniproject.miniprojectgroupthree.repository.UserEntityRepository;
import com.miniproject.miniprojectgroupthree.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.token.secret-key}")
    private String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;

    @Transactional
    public User join(String userName, String password) {
        // 중복 id 체크
        repository.findByUserName(userName)
                .ifPresent(userEntity -> {
                    throw new AppException(ErrorCode.DUPLICATED_USER_NAME,String.format("%s is ducplicated",userName));
                });

        // 회원 가입 진행
        UserEntity userEntity = repository.save(UserEntity.of(userName, passwordEncoder.encode(password)));
        return User.fromEntity(userEntity);
    }

    public String login(String userName, String password) {
        //가입 되었는지
        UserEntity userEntity = repository.findByUserName(userName)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", userName));
                });
        //비밀번호 맞는지
        if (!passwordEncoder.matches(password,userEntity.getPassword()))
        {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }
        //토큰 생성

        return JwtTokenUtils.generalToken(userName, secretKey, expiredTimeMs);
    }
}