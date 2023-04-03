package com.miniproject.miniprojectgroupthree.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;


@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;
//    @Column(nullable = false)
    private String password;
//    @Column(nullable = false)
    private String name;
//    @Column(nullable = false)
    private String birthday;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Column(name = "registered_at")
    private Timestamp registeredAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "logined_at")
    private Timestamp loginedAt;

    @PrePersist
    void registeredAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
        this.loginedAt = Timestamp.from(Instant.now());
    }

    public static UserEntity of(String userName, String password) {
        UserEntity entity = new UserEntity();
        entity.setUserName(userName);
        entity.setPassword(password);
        return entity;
    }
}


