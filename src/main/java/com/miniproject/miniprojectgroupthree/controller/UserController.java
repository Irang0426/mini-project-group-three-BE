package com.miniproject.miniprojectgroupthree.controller;

import com.miniproject.miniprojectgroupthree.controller.request.UserJoinRequest;
import com.miniproject.miniprojectgroupthree.controller.request.UserLoginRequest;
import com.miniproject.miniprojectgroupthree.controller.response.Response;
import com.miniproject.miniprojectgroupthree.controller.response.UserJoinResponse;
import com.miniproject.miniprojectgroupthree.controller.response.UserLoginResponse;
import com.miniproject.miniprojectgroupthree.domain.dto.User;
import com.miniproject.miniprojectgroupthree.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {

        User userDTO = userService.join(request.getUserName(), request.getPassword());
        return Response.success(UserJoinResponse.fromUser(userDTO));
    }
    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        String token = userService.login(request.getUserName(), request.getPassword());

        return Response.success(new UserLoginResponse(token));
    }
}
