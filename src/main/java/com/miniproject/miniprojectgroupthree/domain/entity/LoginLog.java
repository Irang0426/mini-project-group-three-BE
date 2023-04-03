package com.miniproject.miniprojectgroupthree.domain.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Table(name = "login_log")
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "client_ip")
    private String clientIp;

    @Column(name = "login_time")
    private Timestamp loginTime;


    public LoginLog(String memberId, String userAgent, String clientIp, Timestamp loginTime) {
        this.memberId = memberId;
        this.userAgent = userAgent;
        this.clientIp = clientIp;
        this.loginTime = loginTime;
    }

    // getter, setter
}
