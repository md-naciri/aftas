package com.example.aftas.VM;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RankingRequestVM(
        @NotNull(message = "Member number cannot be null")
        Long member_number,
        @NotEmpty(message = "Competition code cannot be empty")
        String competition_code
) {
}
