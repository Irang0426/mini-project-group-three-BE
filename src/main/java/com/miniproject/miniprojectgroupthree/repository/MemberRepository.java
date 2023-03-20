package com.miniproject.miniprojectgroupthree.repository;


import com.miniproject.miniprojectgroupthree.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 계정으로 Optional<Member>받아오기
     *
     * @param account the account
     * @return the optional
     */
    Optional<Member> findByAccount (String account);

}