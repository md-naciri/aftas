package com.example.aftas.VM;

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
}
