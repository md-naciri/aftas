package com.example.aftas.controller;

import com.example.aftas.VM.MemberRequestVM;
import com.example.aftas.VM.MemberResponseVM;
import com.example.aftas.domain.Member;
import com.example.aftas.service.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aftas/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping
    public ResponseEntity<?> createMember (@RequestBody @Valid MemberRequestVM memberRequestVM){
        Member member = memberRequestVM.toMember();
        return ResponseEntity.ok().body(memberService.createMember(member));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMember(@PathVariable("id") Long id){
        MemberResponseVM memberResponseVM = MemberResponseVM.fromMember(memberService.getMember(id));
        return ResponseEntity.ok().body(memberResponseVM);
    }
}
