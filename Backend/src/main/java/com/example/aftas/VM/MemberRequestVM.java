package com.example.aftas.VM;

import com.example.aftas.domain.Member;
import com.example.aftas.enums.IdentityDocumentType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record MemberRequestVM(
        @NotEmpty(message = "First name cannot be empty")
        String firstName,

        @NotEmpty(message = "Last name cannot be empty")
        String lastName,

        @NotNull(message = "Accession date cannot be null")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate accessionDate,

        @NotEmpty(message = "Nationality cannot be empty")
        String nationality,

        @NotNull(message = "Identity document type cannot be null")
        @Enumerated(EnumType.STRING)
        IdentityDocumentType identityDocument,

        @NotEmpty(message = "Identity Number cannot be empty")
        String identityNumber,
        java.util.List<com.example.aftas.domain.Ranking> listOfRanking) {
    public Member toMember(){
        return new Member().builder()
                .firstName(firstName)
                .lastName(lastName)
                .accessionDate(accessionDate)
                .nationality(nationality)
                .identityDocument(identityDocument)
                .identityNumber(identityNumber)
                .build();
    }
}
