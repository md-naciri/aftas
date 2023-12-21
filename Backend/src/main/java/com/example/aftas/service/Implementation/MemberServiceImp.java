package com.example.aftas.service.Implementation;

import com.example.aftas.domain.Member;
import com.example.aftas.handler.OperationException;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    public Member createMember(Member member) {
        //Optional<Member> isMemberExist = memberRepository.findById(member.getNumber());
        Optional<Member> isMemberExist = memberRepository.findByIdentityNumber(member.getIdentityNumber());
        if (isMemberExist.isPresent()){
            throw new OperationException("Member already exists");
        }
        Optional<Member> isMemberExist1 = isMemberExist;
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
    @Override
    public List<Member> findByNumberOrFirstNameOrLastName(String search) {
        if(isStringLong(search)){
            Long number = Long.parseLong(search);
            List<Member> membersList = memberRepository.findByNumber(number);
            if(membersList.isEmpty()) throw new OperationException("There is no member with this number/name");
            else return membersList;
        }
        List<Member> membersList = memberRepository.findByFirstNameOrLastName(search, search);
        if(membersList.isEmpty()) throw new OperationException("There is no member with this number/name");
        else return membersList;
    }
    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }
    public boolean isStringLong(String search) {
        try {
            Long.parseLong(search);
            return true; // Parsing successful
        } catch (NumberFormatException e) {
            return false; // Parsing failed
        }
    }
}
