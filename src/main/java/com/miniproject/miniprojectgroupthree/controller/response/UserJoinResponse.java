package com.miniproject.miniprojectgroupthree.controller.response;

import com.miniproject.miniprojectgroupthree.domain.dto.User;
import com.miniproject.miniprojectgroupthree.domain.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinResponse {
    private Long id;
    private String userName;
    private UserRole userRole;


    public static UserJoinResponse fromUser(User user) {
        return new UserJoinResponse(
                user.getId(),
                user.getUserName(),
                user.getRole()
        );
    }
}
