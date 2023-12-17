package com.example.aftas.VM;

import com.example.aftas.domain.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record HuntingRequestVM(
        @NotNull(message = "Member number cannot be null")
        Long memberNumber,
        @NotEmpty(message = "Competition code cannot be empty")
        String competitionCode,
        @NotEmpty(message = "Fish name cannot be empty")
        String fishName,
        @NotNull(message = "Fish weight cannot be null")
        @Positive(message = "Fish weight must be a positive value")
        Double fishWeight
) {
        public Hunting toHunting(){
                return Hunting.builder()
                        .member(Member.builder().number(memberNumber).build())
                        .competition(Competition.builder().code(competitionCode).build())
                        .fish(Fish.builder().name(fishName).averageWeight(fishWeight).build())
                        .build();

        }
}
