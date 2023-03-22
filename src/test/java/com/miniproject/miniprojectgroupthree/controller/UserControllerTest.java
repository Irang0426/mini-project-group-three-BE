package com.miniproject.miniprojectgroupthree.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miniproject.miniprojectgroupthree.controller.request.UserJoinRequest;
import com.miniproject.miniprojectgroupthree.controller.request.UserLoginRequest;
import com.miniproject.miniprojectgroupthree.domain.User;
import com.miniproject.miniprojectgroupthree.exception.AppException;
import com.miniproject.miniprojectgroupthree.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;
    @Test
    @DisplayName("회원가입 성공")
    public void join() throws Exception {
        String userName = "username";
        String password = "password";

        when(userService.join(userName,password))
                .thenReturn(mock(User.class));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                new UserJoinRequest(userName,password))
                        ))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패 - 아이디중복")
    public void join_fail() throws Exception {
        String userName = "username";
        String password = "password";

        when(userService.join(userName, password))
                .thenThrow(new AppException());

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                new UserJoinRequest(userName,password))

                        ))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("로그인 성공")
    public void login() throws Exception {
        String userName = "username";
        String password = "password";

        when(userService.login(userName,password))
                .thenReturn("token");

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                new UserLoginRequest(userName,password))
                        ))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 실패-가입 안된 아이디로 로그인")
    public void login_fail1() throws Exception {
        String userName = "username";
        String password = "password";

        when(userService.login(userName,password))
                .thenThrow(new AppException());

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                new UserLoginRequest(userName,password))
                        ))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 틀림")
    public void login_fail2() throws Exception {
        String userName = "username";
        String password = "password";

        when(userService.login(userName,password))
                .thenThrow(new AppException());

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                new UserLoginRequest(userName,password))
                        ))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
