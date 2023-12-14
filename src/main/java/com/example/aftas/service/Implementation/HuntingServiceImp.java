package com.example.aftas.service.Implementation;

import com.example.aftas.domain.*;
import com.example.aftas.handler.OperationException;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HuntingServiceImp implements HuntingService {
    private final HuntingRepository huntingRepository;
    private final FishService fishService;
    private final RankingService rankingService;
    private final MemberService memberService;
    private final CompetitionService competitionService;
    @Override
    public Hunting createHunting(Long memberNumber, String competitionCode, String fishName, Double fishWeight) {
        Ranking memberRegistered = rankingService.getRanking(memberNumber, competitionCode);
        Fish fish = fishService.getFish(fishName);
        if (!compareWeight(fishWeight, fish.getAverageWeight())){
            throw new OperationException("The weight of that fish is lower than the average weight");
        }
        Member member = memberService.getMember(memberNumber);
        Competition competition = competitionService.getCompetition(competitionCode);
        Hunting hunting = doesMemberHuntFish(memberNumber, competitionCode, fishName);
        if (hunting!=null){
            return huntingRepository.save(
                    Hunting.builder()
                            .id(hunting.getId())
                            .numberOfFish(hunting.getNumberOfFish()+1)
                            .fish(fish)
                            .member(member)
                            .competition(competition)
                            .build()
            );

        }
        else {
            return huntingRepository.save(
                    Hunting.builder()
                            .numberOfFish(1)
                            .fish(fish)
                            .member(member)
                            .competition(competition)
                            .build()
            );
        }

        // I have now to calculate the score on the ranking table or update it
    }

    @Override
    public Hunting doesMemberHuntFish(Long memberNumber, String competitionCode, String fishName) {
        Member member = memberService.getMember(memberNumber);
        Competition competition = competitionService.getCompetition(competitionCode);
        Fish fish = fishService.getFish(fishName);
        Optional<Hunting> hunting = huntingRepository.findByMemberAndCompetitionAndFish(member, competition, fish);
        return hunting.orElse(null);
    }

    public boolean compareWeight(Double fishWeight, Double fishAverageWeight){
        return fishWeight >= fishAverageWeight;
    }

}
