package com.example.aftas.domain;

import com.example.aftas.enums.IdentityDocumentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long number;

    private String firstName;

    private String lastName;

    private LocalDate accessionDate;

    private String nationality;

    private IdentityDocumentType identityDocument;

    private String identityNumber;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Hunting> ListOfHunting;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Ranking> listOfRanking;
}
