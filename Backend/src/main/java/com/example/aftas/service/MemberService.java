package com.example.aftas.service;

import com.example.aftas.domain.Member;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberService {
    Member createMember (Member member);
    Member getMember (Long id);
    List<Member> findByNumberOrFirstNameOrLastName(String search);

    List<Member> getMembers();
}
