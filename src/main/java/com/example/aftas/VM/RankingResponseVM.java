package com.example.aftas.VM;

import com.example.aftas.domain.Ranking;

import java.time.LocalDate;
import java.time.LocalTime;

public record RankingResponseVM(
        String Fist_Name,
        String Last_Name,
        String Competition_Code,
        String Competition_Location,
        LocalDate Competition_Date,
        Integer Rank,
        Integer Score
) {
    public static RankingResponseVM rankingResponse(Ranking ranking){
        return new RankingResponseVM(
                ranking.getMember().getFirstName(),
                ranking.getMember().getLastName(),
                ranking.getCompetition().getCode(),
                ranking.getCompetition().getLocation(),
                ranking.getCompetition().getDate(),
                ranking.getRaank(),
                ranking.getScore()
        );
    }
}
