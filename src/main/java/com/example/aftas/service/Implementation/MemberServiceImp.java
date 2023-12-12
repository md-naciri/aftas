package com.example.aftas.service.Implementation;

import com.example.aftas.domain.Member;
import com.example.aftas.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImp implements MemberService {
    @Override
    public Member createMember(Member member) {
        return member;
    }

    @Override
    public Member getMember(Long id) {
        return null;
    }
}
