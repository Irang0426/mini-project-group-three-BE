package com.miniproject.miniprojectgroupthree.domain.dto;


import com.miniproject.miniprojectgroupthree.domain.entity.UserEntity;
import com.miniproject.miniprojectgroupthree.domain.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Getter
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().name()));
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
