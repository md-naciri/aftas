package com.example.aftas.controller;

import com.example.aftas.VM.MemberRequestVM;
import com.example.aftas.VM.MemberResponseVM;
import com.example.aftas.domain.Level;
import com.example.aftas.domain.Member;
import com.example.aftas.handler.ResponseHandler;
import com.example.aftas.service.LevelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aftas/api/v1/level")
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;
    @PostMapping()
    public ResponseEntity<?> createLevel (@RequestBody @Valid Level level){
        levelService.createLevel(level);
        return ResponseHandler.created(
                level,"Level created successfully"
        );
    }
}
