package com.example.aftas.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfFish;
    @ManyToOne
    private Fish fish;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Competition competition;
}
