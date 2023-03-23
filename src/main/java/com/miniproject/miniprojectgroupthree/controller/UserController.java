package com.miniproject.miniprojectgroupthree.controller;

import com.miniproject.miniprojectgroupthree.controller.request.UserJoinRequest;
import com.miniproject.miniprojectgroupthree.controller.response.Response;
import com.miniproject.miniprojectgroupthree.controller.response.UserJoinResponse;
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
        UserJoinResponse result = UserJoinResponse.fromUser(userDTO);
        return Response.success(result);
    }
    @PostMapping("/login")
    public void login() {

    }
}
