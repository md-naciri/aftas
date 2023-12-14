package com.example.aftas.VM;

import com.example.aftas.domain.Hunting;
import com.example.aftas.domain.Ranking;
import lombok.Setter;

public record HuntingResponseVM(
        String Fist_Name,
        String Last_Name,
        String Competition_Code,
        String Fish_Name,
        Integer Fish_Level,
        Integer Level_Points,
        Integer NumberOfFish,
        //Integer Score,
        Integer Total_Score
) {
    public static HuntingResponseVM huntingResponseVM(Hunting hunting){
        return new HuntingResponseVM(
                hunting.getMember().getFirstName(),
                hunting.getMember().getLastName(),
                hunting.getCompetition().getCode(),
                hunting.getFish().getName(),
                hunting.getFish().getLevel().getCode(),
                hunting.getFish().getLevel().getPoints(),
                hunting.getNumberOfFish(),
                //0,
                hunting.getMember().getListOfRanking().stream()
                        .filter(ranking -> ranking.getCompetition().equals((hunting.getCompetition()))).map(Ranking::getScore).findFirst().orElse(0)
        );

    }
}
