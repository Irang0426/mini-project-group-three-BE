package com.miniproject.miniprojectgroupthree.controller;

import com.miniproject.miniprojectgroupthree.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/join")
    public void join(String userName,String password) {

        userService.join(userName, password);
    }

    @PostMapping("/login")
    public void login() {

    }
}
