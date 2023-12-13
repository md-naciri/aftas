package com.example.aftas.service;

import com.example.aftas.domain.Ranking;

public interface RankingService {
    Ranking registerMemberForCompetition(Long number, String code);
    Ranking getCompetition (Long number, String code);
}
