package com.example.aftas.controller;

import com.example.aftas.domain.Member;
import com.example.aftas.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Controller
@RestController
@RequestMapping("/aftas/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping
    public ResponseEntity<?> createMember (@RequestBody Member member){
        return ResponseEntity.ok().body(memberService.createMember(member));
    }
}
