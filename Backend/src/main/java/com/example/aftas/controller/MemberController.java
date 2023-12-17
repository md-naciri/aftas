package com.example.aftas.controller;

import com.example.aftas.VM.MemberRequestVM;
import com.example.aftas.VM.MemberResponseVM;
import com.example.aftas.domain.Member;
import com.example.aftas.handler.ResponseHandler;
import com.example.aftas.service.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aftas/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping
    public ResponseEntity<?> createMember (@RequestBody @Valid MemberRequestVM memberRequestVM){
        Member member = memberService.createMember(memberRequestVM.toMember());
        return ResponseHandler.created(
                MemberResponseVM.fromMember(member),"Member created successfully"
        );
        //return ResponseEntity.ok().body(memberService.createMember(member));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMember(@PathVariable("id") Long id){
        MemberResponseVM memberResponseVM = MemberResponseVM.fromMember(memberService.getMember(id));

        return ResponseHandler.ok(
                memberResponseVM, "Member Found Successfully"
        );
        //return ResponseEntity.ok().body(memberResponseVM);
    }

    @GetMapping("/search/{search}")
    public ResponseEntity<?> searchForMembers(@PathVariable("search") String search){
        List<Member> listOfMembers = memberService.findByNumberOrFirstNameOrLastName(search);
        List<MemberResponseVM> membersResponse = MemberResponseVM.fromListOfMembers(listOfMembers);
        return ResponseHandler.ok(
                membersResponse, "This is the result of your search"
        );
    }
}
