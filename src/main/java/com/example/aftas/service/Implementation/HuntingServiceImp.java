package com.example.aftas.service.Implementation;

import com.example.aftas.domain.*;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HuntingServiceImp implements HuntingService {
    private final HuntingRepository huntingRepository;
    private final FishService fishService;
    private final RankingService rankingService;
    @Override
    public Hunting createHunting(Long memberNumber, String competitionCode, String fishName, Double fishWeight) {
        Ranking memberRegistered = rankingService.getRanking(memberNumber, competitionCode);
        Fish fish = fishService.getFish(fishName);
        return null;
    }

    /*

        Member member = memberService.getMember(number);
        Competition competition = competitionService.getCompetition(code);
        Ranking rankingByMemberAndCompetition = rankingRepository.findRankingByMemberAndCompetition(member, competition);
        if(rankingByMemberAndCompetition != null){
            throw new OperationException("This member is already registered to this competition");
        }
        if(dateTimeComparison(competition.getDate(),competition.getStartTime())){
            throw new OperationException("If there's less than 24 hours left before the competition starts, you can't register");
        }
        return rankingRepository.save(
                Ranking.builder()
                        .id(RankId.builder()
                                .member_number(member.getNumber())
                                .competition_code(competition.getCode())
                                .build()
                        )
                        .member(member)
                        .competition(competition)
                        .raank(0)
                        .score(0)
                        .build()
        );

     */
}
