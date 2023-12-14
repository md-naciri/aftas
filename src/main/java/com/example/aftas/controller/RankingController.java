package com.example.aftas.controller;

import com.example.aftas.VM.CompetitionResponseVM;
import com.example.aftas.VM.RankingRequestVM;
import com.example.aftas.VM.RankingResponseVM;
import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;
import com.example.aftas.handler.ResponseHandler;
import com.example.aftas.service.RankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/aftas/api/v1/ranking")
@RequiredArgsConstructor
public class RankingController {
    private final RankingService rankingService;
    @PostMapping()
    public ResponseEntity<?> registerMemberForCompetition(@RequestBody @Valid RankingRequestVM rankingRequestVM){
        Ranking ranking = rankingService.registerMemberForCompetition(rankingRequestVM.member_number(), rankingRequestVM.competition_code());
        return ResponseHandler.created(RankingResponseVM.rankingResponse(ranking), "Member registered successfully for the competition");
    }
    @GetMapping("/member/{number}/competition/{code}")
    public  ResponseEntity<?> getRanking(@PathVariable("number") Long id, @PathVariable("code") String code){
        Ranking ranking = rankingService.getRanking(id, code);
        RankingResponseVM rankingResponseVM = RankingResponseVM.rankingResponse(ranking);
        return ResponseHandler.ok(rankingResponseVM, "Ranking Found Successfully");
    }
    @GetMapping("/competition/{code}")
    public ResponseEntity<?> getCompetitionRank(@PathVariable("code") String competitionCode){
        List<Ranking> rankings = rankingService.listScores(competitionCode);
        Map<Object, Object> rankingResponse = new HashMap<>();
        rankingResponse.put("Competition", competitionCode);
        rankings.forEach(ranking -> rankingResponse.put(ranking.getRaank(), List.of(ranking.getMember().getFirstName(), ranking.getMember().getLastName())));
        return ResponseHandler.ok(rankingResponse, "List of ranks successfully generated");
    }

}
