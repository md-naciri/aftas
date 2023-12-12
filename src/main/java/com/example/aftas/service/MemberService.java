package com.example.aftas.service;

import com.example.aftas.domain.Member;

public interface MemberService {
    Member createMember (Member member);
    Member getMember (Long id);
}
