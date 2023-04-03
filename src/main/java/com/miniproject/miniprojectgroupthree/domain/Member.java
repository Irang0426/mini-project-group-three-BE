package com.miniproject.miniprojectgroupthree.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;


@Data
@Builder
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @Column(nullable = false)
    private String account;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String birth;
    @Column(nullable = false)
    private Role role;

    private LocalDateTime loginDate;
    private LocalDateTime modifyDate;
    @Column(nullable = false)
    private LocalDateTime joinDate;

    @PrePersist
    protected void onCreate() {
        joinDate = LocalDateTime.now();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> role.name());
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
