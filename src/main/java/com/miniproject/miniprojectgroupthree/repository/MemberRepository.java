package com.miniproject.miniprojectgroupthree.repository;

import com.miniproject.miniprojectgroupthree.entity.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

  Optional<MemberEntity> findByMemberEmail(String memberEmail);
 }

