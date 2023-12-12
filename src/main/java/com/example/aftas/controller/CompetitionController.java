package com.example.aftas.controller;

import com.example.aftas.domain.Competition;
import com.example.aftas.service.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aftas/api/v1/competition")
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionService competitionService;
    @PostMapping
    public ResponseEntity<?> createCompetition (@RequestBody @Valid Competition competition){
        return ResponseEntity.ok().body(competitionService.createCompetition(competition));
    }
    @GetMapping("{code}")
    public ResponseEntity<?> getCompetition(@PathVariable("code") String code){
        return ResponseEntity.ok().body(competitionService.getCompetition(code));
    }
}
