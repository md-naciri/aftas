package com.example.aftas.repository;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;
import com.example.aftas.domain.embeddable.RankId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankId> {
    Ranking findRankingByMemberAndCompetition(Member member, Competition competition);
    List<Ranking> findAllByCompetition_CodeOrderByScoreDesc(String competitionCode);
}
