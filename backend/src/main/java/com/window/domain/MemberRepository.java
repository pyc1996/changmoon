package com.window.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    //아이디확인
    Optional<Member> findByMemberId(String memberId);
    //중복체크
    boolean existsByMemberId(String memberId);


}
