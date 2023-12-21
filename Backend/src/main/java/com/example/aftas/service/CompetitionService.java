package com.example.aftas.service;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface CompetitionService {
    Competition createCompetition (Competition competition);
    Competition getCompetition (String code);
    Competition updateCompetition(Competition competition);
    Page<Competition> getCompetitionsPagination(Pageable pageable);
    List<Competition> getCompetitions();
    List<Competition> filterCompetitionsInProgress();
    List<Competition> filterCompetitionsClosed();

}
