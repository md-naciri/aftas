package com.example.aftas.VM;

import com.example.aftas.domain.Competition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionRequestVM(
        @NotNull(message = "Date cannot be null")
        LocalDate date,
        @NotNull(message = "Start time cannot be null")
        LocalTime startTime,
        @NotNull(message = "End time cannot be null")
        LocalTime endTime,
        @NotBlank(message = "Location cannot be blank")
        String location,
        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be a positive number")
        Double amount
) {
   public Competition toCompetition(){
       return new Competition().builder()
               .date(date)
               .startTime(startTime)
               .endTime(endTime)
               .location(location)
               .amount(amount)
               .build();
   }
}
