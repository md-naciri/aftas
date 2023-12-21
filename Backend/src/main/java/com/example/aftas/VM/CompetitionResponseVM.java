package com.example.aftas.VM;

import com.example.aftas.domain.Competition;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionResponseVM(
        String code,
        LocalDate date,
        LocalTime Start_Time,
        LocalTime End_Time,
        Integer Number_Of_Participants,
        String location,
        Double amount

) {
    public static CompetitionResponseVM fromCompetition(Competition competition){
        return new CompetitionResponseVM(
                competition.getCode(),
                competition.getDate(),
                competition.getStartTime(),
                competition.getEndTime(),
                competition.getNumberOfParticipants(),
                competition.getLocation(),
                competition.getAmount()
        );
    }
}
