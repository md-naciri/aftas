package com.example.aftas.VM;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FishRequestVM(
        @NotBlank(message = "Name must not be empty")
        String name,
        @NotNull(message = "Average weight must not be null")
        @Positive(message = "Average weight must be positive")
        Double averageWeight,
        @NotNull(message = "Level code must not be null")
        Integer levelCode
) {
}
