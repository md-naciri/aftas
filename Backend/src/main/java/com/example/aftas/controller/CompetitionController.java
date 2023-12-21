package com.example.aftas.controller;

import com.example.aftas.VM.CompetitionRequestVM;
import com.example.aftas.VM.CompetitionResponseVM;
import com.example.aftas.domain.Competition;
import com.example.aftas.handler.ResponseHandler;
import com.example.aftas.service.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aftas/api/v1/competition")
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionService competitionService;
    @PostMapping
    public ResponseEntity<?> createCompetition (@RequestBody @Valid CompetitionRequestVM competitionRequestVM){
        Competition competition = competitionService.createCompetition(competitionRequestVM.toCompetition());
        return ResponseHandler.created(CompetitionResponseVM.fromCompetition(competition), "Competition created successfully");
    }
    @GetMapping("{code}")
    public ResponseEntity<?> getCompetition(@PathVariable("code") String code){
        CompetitionResponseVM competitionResponseVM = CompetitionResponseVM.fromCompetition(competitionService.getCompetition(code));
        return ResponseHandler.ok(competitionResponseVM, "Competition Found Successfully");
    }
    @GetMapping("/pagination")
    public ResponseEntity<?> getCompetitionsPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        Page<Competition> competitions = competitionService.getCompetitionsPagination(PageRequest.of(page, size));
        List<CompetitionResponseVM> response = new ArrayList<>();

        for (Competition competition : competitions) {
            response.add(CompetitionResponseVM.fromCompetition(competition));
        }
        return ResponseHandler.ok(response, "Competitions Found Successfully");
    }
    @GetMapping
    public ResponseEntity<?> getCompetitions(){
        List<Competition> competitions = competitionService.getCompetitions();
        List<CompetitionResponseVM> response = new ArrayList<>();

        for (Competition competition : competitions) {
            response.add(CompetitionResponseVM.fromCompetition(competition));
        }
        return ResponseHandler.ok(response, "Competitions Found Successfully");
    }
    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getCompetitionsfilter(@PathVariable("filter") String filterType){
        List<Competition> competitions;
        if (!"closed".equals(filterType)){
            competitions = competitionService.filterCompetitionsInProgress();
        } else {
            competitions = competitionService.filterCompetitionsClosed();
        }
        List<Competition> competitions1 = competitions;
        List<CompetitionResponseVM> response = new ArrayList<>();
        for (Competition competition : competitions) {
            response.add(CompetitionResponseVM.fromCompetition(competition));
        }
        return ResponseHandler.ok(response, "Competitions Found Successfully");
    }

}
