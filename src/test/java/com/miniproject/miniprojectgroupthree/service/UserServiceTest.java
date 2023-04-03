package com.miniproject.miniprojectgroupthree.service;

import com.miniproject.miniprojectgroupthree.domain.entity.UserEntity;
import com.miniproject.miniprojectgroupthree.domain.entity.UserEntityFixture;
import com.miniproject.miniprojectgroupthree.exception.AppException;
import com.miniproject.miniprojectgroupthree.repository.UserEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private BCryptPasswordEncoder passwordEncoder;
    @MockBean
    private UserEntityRepository repository;

    @Test
    @DisplayName("회원가입 성공")
    void join() {
        String userName = "user1";
        String password = "1234";
        UserEntity entity = UserEntityFixture.get(userName, password);
        when(repository.findByUserName(userName))
                .thenReturn(Optional.empty());
        when(repository.save(any()))
                .thenReturn(entity);

        assertDoesNotThrow(() -> userService.join(userName,password));
    }

    @Test
    @DisplayName("회원가입 실패 - 해당 아이디가 이미 있는경우")
    void join_exists() {
        String userName = "user1";
        String password = "1234";
        UserEntity entity = UserEntityFixture.get(userName, password);
        when(repository.findByUserName(userName))
                .thenReturn(Optional.of(entity));
        when(repository.save(any()))
                .thenReturn(Optional.of(entity));

        Assertions.assertThrows(AppException.class, () -> userService.join(userName, password));
    }
    @Test
    @DisplayName("로그인 성공")
    void login() {
        String userName = "user1";
        String password = "1234";
        UserEntity entity = UserEntityFixture.get(userName, password);
        when(repository.findByUserName(userName))
                .thenReturn(Optional.of(entity));
        when(passwordEncoder.matches(password, entity.getPassword()))
                .thenReturn(true);
        assertDoesNotThrow(() -> userService.login(userName,password));
    }

    @Test
    @DisplayName("로그인 실패 - 가입 안된경우")
    void login_fail() {
        String userName = "user1";
        String password = "1234";

        when(repository.findByUserName(userName)).thenReturn(Optional.empty());
        Assertions.assertThrows(AppException.class,() -> userService.login(userName, password));
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 다른경우")
    void login_fail2() {
        String userName = "user1";
        String password = "1234";
        String wrongPassword = "12345";

        UserEntity entity = UserEntityFixture.get(userName, password);
        when(repository.findByUserName(userName))
                .thenReturn(Optional.of(entity));

        Assertions.assertThrows(AppException.class, () -> userService.login(userName, wrongPassword));
    }
}