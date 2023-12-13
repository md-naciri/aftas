package com.example.aftas.service;

import com.example.aftas.domain.Ranking;

public interface RankingService {
    Ranking registerMemberForCompetition(Long number, String code);
    Ranking getRanking (Long number, String code);
}
