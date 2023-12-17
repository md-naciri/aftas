package com.example.aftas.service;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;

import java.util.List;

public interface RankingService {
    Ranking registerMemberForCompetition(Long number, String code);
    Ranking getRanking (Long number, String code);
    //Ranking calculateScore(Fish fish, Ranking ranking, Member member, Competition competition);
    Ranking calculateScore(Fish fish, Ranking ranking);
    List<Ranking> listScores(String codeCompetition);
}
