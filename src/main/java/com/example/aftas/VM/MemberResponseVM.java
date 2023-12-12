package com.example.aftas.VM;

import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;
import com.example.aftas.enums.IdentityDocumentType;

import java.time.LocalDate;
import java.util.List;

public record MemberResponseVM(
        String Fist_Name,
        String Last_Name,
        LocalDate Accession_Date,
        String Nationality,
        IdentityDocumentType Identity_Document_Type,
        String Identity_Number,
        List<Ranking> List_Of_Ranking
) {
    public static MemberResponseVM fromMember(Member member){
        return new MemberResponseVM(
                member.getFirstName(),
                member.getLastName(),
                member.getAccessionDate(),
                member.getNationality(),
                member.getIdentityDocument(),
                member.getIdentityNumber(),
                member.getListOfRanking()
        );
    }
}