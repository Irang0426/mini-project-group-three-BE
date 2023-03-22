package com.miniproject.miniprojectgroupthree.service;

import com.miniproject.miniprojectgroupthree.domain.User;
import com.miniproject.miniprojectgroupthree.domain.entity.UserEntity;
import com.miniproject.miniprojectgroupthree.exception.AppException;
import com.miniproject.miniprojectgroupthree.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository repository;

    public User join(String userName, String password) {
        // 중복 id 체크
        Optional<UserEntity> entity = repository.findByUserName(userName);
        
        // 회원 가입 진행
        repository.save(new UserEntity());
        return new User();
    }

    public String login(String userName, String password) {
        //가입 되었는지
        UserEntity userEntity = repository.findByUserName(userName)
                            .orElseThrow(AppException::new);
        //비밀번호 맞는지
        if (!userEntity.getPassword().equals(password)) {
            throw new AppException();
        }
        //토큰 생성
        return "";
    }
}
