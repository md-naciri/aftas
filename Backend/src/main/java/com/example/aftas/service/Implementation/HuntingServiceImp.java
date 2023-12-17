package com.example.aftas.service.Implementation;

import com.example.aftas.domain.*;
import com.example.aftas.handler.OperationException;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public Hunting createHunting(Hunting huntingRec) {
        Long memberNumber = huntingRec.getMember().getNumber();
        String competitionCode = huntingRec.getCompetition().getCode();
        String fishName = huntingRec.getFish().getName();
        Double fishWeight = huntingRec.getFish().getAverageWeight();
        Ranking memberRegistered = rankingService.getRanking(memberNumber, competitionCode);

        // check fish
        Fish fish = fishService.getFish(fishName);
        if (!compareWeight(fishWeight, fish.getAverageWeight())) throw new OperationException("The weight of that fish is lower than the average weight");

        //check competition
        Competition competition = competitionService.getCompetition(competitionCode);
        if (!isHuntBetweenStartAndEndCompetitionTime(competition.getDate(),competition.getStartTime())) throw new OperationException("You are not allowed to submit an entry before the competition begins or after the day has ended");

        //check member
        Member member = memberService.getMember(memberNumber);

        // check hunting
        Hunting hunting =  huntingRepository.findByMemberAndCompetitionAndFish(member, competition, fish).orElse(null);

        rankingService.calculateScore(fish, memberRegistered);

        Hunting.HuntingBuilder huntingBuilder = Hunting.builder()
                .fish(fish)
                .member(member)
                .competition(competition);
        if (hunting!=null) huntingBuilder.id(hunting.getId()).numberOfFish(hunting.getNumberOfFish()+1);
        else huntingBuilder.numberOfFish(1);
        return huntingRepository.save(huntingBuilder.build());
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

    public boolean isHuntBetweenStartAndEndCompetitionTime(LocalDate date, LocalTime startTime){
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime competitionStartTime = LocalDateTime.of(date, startTime);
        LocalDateTime competitionEndTime = LocalDateTime.of(date, LocalTime.MAX);
        return currentDateTime.isAfter(competitionStartTime) && currentDateTime.isBefore(competitionEndTime);
    }

}
