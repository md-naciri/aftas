package com.example.aftas.domain;

import com.example.aftas.enums.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;
    private String firstName;
    private String lastName;
    private LocalDate accessionDate;
    private String nationality;
    private IdentityDocumentType identityDocument;
    private String identityNumber;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Hunting> ListOfHunting;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Ranking> listOfRanking;
}
