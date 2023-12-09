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
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String description;
    private Integer points;
    @OneToMany(mappedBy = "level", fetch = FetchType.LAZY)
    private List<Fish> fishes;
}
