package com.example.aftas.domain;

import com.example.aftas.enums.IdentityDocumentType;
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
    private Long id;

    @NotNull(message = "Number cannot be null")
    private Long number;

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @NotNull(message = "Accession date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate accessionDate;

    @NotEmpty(message = "Nationality cannot be empty")
    private String nationality;

    @NotNull(message = "Identity document type cannot be null")
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;

    @NotEmpty(message = "Identity Number cannot be empty")
    private String identityNumber;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Hunting> ListOfHunting;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Ranking> listOfRanking;
}
