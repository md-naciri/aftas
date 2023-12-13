package com.example.aftas.controller;

import com.example.aftas.VM.CompetitionRequestVM;
import com.example.aftas.VM.CompetitionResponseVM;
import com.example.aftas.domain.Competition;
import com.example.aftas.handler.ResponseHandler;
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
    public ResponseEntity<?> createCompetition (@RequestBody @Valid CompetitionRequestVM competitionRequestVM){
        Competition competition = competitionService.createCompetition(competitionRequestVM.toCompetition());
        return ResponseHandler.created(CompetitionResponseVM.fromCompetition(competition), "Competition created successfully");
        //return ResponseEntity.ok().body(competitionService.createCompetition(competition));
    }
    @GetMapping("{code}")
    public ResponseEntity<?> getCompetition(@PathVariable("code") String code){
        CompetitionResponseVM competitionResponseVM = CompetitionResponseVM.fromCompetition(competitionService.getCompetition(code));
        return ResponseHandler.ok(competitionResponseVM, "Competition Found Successfully");
        //return ResponseEntity.ok().body(competitionService.getCompetition(code));
    }
}
