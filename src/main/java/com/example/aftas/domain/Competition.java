package com.example.aftas.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Competition {
    @Id
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;
    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Hunting> listOfHunting;
    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Ranking> listOfRanking;
}
