package com.miniproject.miniprojectgroupthree.domain.dto;


import com.miniproject.miniprojectgroupthree.domain.entity.UserEntity;
import com.miniproject.miniprojectgroupthree.domain.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
@AllArgsConstructor
@Getter
public class User {
    private Long id;
    private String userName;
    private String password;
    private String name;
    private UserRole role;
    private String phoneNunber;
    private String birthday;
    private Timestamp registedAt;
    private Timestamp updatedAt;
    private Timestamp loginedAt;
    public static User fromEntity(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getName(),
                entity.getRole(),
                entity.getPhoneNumber(),
                entity.getBirthday(),
                entity.getRegisteredAt(),
                entity.getUpdatedAt(),
                entity.getLoginedAt()
        );
    }
}
