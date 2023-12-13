package com.example.aftas.service.Implementation;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.service.CompetitionService;
import com.example.aftas.service.MemberService;
import com.example.aftas.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankingServiceImp implements RankingService {
    private final RankingRepository rankingRepository;
    private final MemberService memberService;
    private final CompetitionService competitionService;
    @Override
    public Ranking registerMemberForCompetition(Long number, String code) {
        Member member = memberService.getMember(number);
        Competition competition = competitionService.getCompetition(code);
        return rankingRepository.save(Ranking.builder().member(member).competition(competition).raank(0).score(0).build());
    }

    @Override
    public Ranking getCompetition(Long number, String code) {
        Member member = memberService.getMember(number);
        Competition competition = competitionService.getCompetition(code);
        return rankingRepository.findRankingByMemberAndCompetition(member, competition);
    }


}
