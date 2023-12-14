package com.example.aftas.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private Double averageWeight;
    @ManyToOne
    private Level level;
    @OneToMany(mappedBy = "fish", fetch = FetchType.LAZY)
    private List<Hunting> ListOfHunting;
}
