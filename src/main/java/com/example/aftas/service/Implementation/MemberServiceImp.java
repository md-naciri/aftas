package com.example.aftas.service.Implementation;

import com.example.aftas.domain.Member;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member getMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()){
            throw new IllegalArgumentException("Member doesn't exist");
        }
        return member.get();
    }
}
