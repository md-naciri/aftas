package com.example.aftas.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Entity
public class Ranking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ranks;
    private Integer scores;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Competition competition;
}