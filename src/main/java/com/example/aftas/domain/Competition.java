package com.example.aftas.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Competition {
    @Id
    @NotBlank(message = "Code cannot be blank")
    private String code;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;

    @NotNull(message = "Number of participants cannot be null")
    private Integer numberOfParticipants;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be a positive number")
    private Double amount;

    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Hunting> listOfHunting;
    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Ranking> listOfRanking;
}
