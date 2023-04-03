package com.miniproject.miniprojectgroupthree.repository;

import com.miniproject.miniprojectgroupthree.domain.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {
}