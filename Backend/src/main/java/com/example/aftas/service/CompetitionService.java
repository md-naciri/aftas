package com.example.aftas.service;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;

import java.time.LocalDate;
import java.util.List;

public interface CompetitionService {
    Competition createCompetition (Competition competition);
    Competition getCompetition (String code);
    Competition updateCompetition(Competition competition);
    List<Competition> getCompetitions();
}
