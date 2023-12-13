package com.example.aftas.controller;

import com.example.aftas.VM.RankingRequestVM;
import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;
import com.example.aftas.handler.ResponseHandler;
import com.example.aftas.service.RankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/aftas/api/v1/ranking")
@RequiredArgsConstructor
public class RankingController {
    private final RankingService rankingService;
    @PostMapping()
    public ResponseEntity<?> registerMemberForCompetition(@RequestBody @Valid RankingRequestVM rankingRequestVM){
        Ranking ranking = rankingService.registerMemberForCompetition(rankingRequestVM.member_number(), rankingRequestVM.competition_code());
        return ResponseHandler.created(ranking, "Member registered successfully for the competition");
    }
    @GetMapping("/member/{number}/competition/{code}")
    public  ResponseEntity<?> getRanking(@PathVariable("number") Long id, @PathVariable("code") String code){
        Ranking ranking = rankingService.getRanking(id, code);
        return ResponseHandler.ok(ranking, "Ranking Found Successfully");
    }

}
