package com.example.aftas.service.Implementation;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;
import com.example.aftas.domain.embeddable.RankId;
import com.example.aftas.handler.OperationException;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.service.CompetitionService;
import com.example.aftas.service.HuntingService;
import com.example.aftas.service.MemberService;
import com.example.aftas.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingServiceImp implements RankingService {
    private final RankingRepository rankingRepository;
    private final MemberService memberService;
    private final CompetitionService competitionService;
    @Override
    public Ranking registerMemberForCompetition(Long memberNumber, String competitionCode) {
        Member member = memberService.getMember(memberNumber);
        Competition competition = competitionService.getCompetition(competitionCode);
        Ranking rankingByMemberAndCompetition = rankingRepository.findRankingByMemberAndCompetition(member, competition);
        if(rankingByMemberAndCompetition != null){
            throw new OperationException("This member is already registered to this competition");
        }
        if(dateTimeComparison(competition.getDate(),competition.getStartTime())){
            throw new OperationException("You cannot register if there are less than 24 hours before the competition starts or if the competition has ended");
        }
        competition.setNumberOfParticipants(competition.getNumberOfParticipants()+1);
        competitionService.updateCompetition(competition);
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
    }

    @Override
    public Ranking getRanking(Long memberNumber, String competitionCode) {
        Member member = memberService.getMember(memberNumber);
        Competition competition = competitionService.getCompetition(competitionCode);
        Ranking rankingByMemberAndCompetition = rankingRepository.findRankingByMemberAndCompetition(member, competition);
        if (rankingByMemberAndCompetition == null){
            throw new IllegalArgumentException("This Member is not registered for this competition");
        }
        return rankingByMemberAndCompetition;
    }

    @Override
    public Ranking calculateScore(Fish fish, Ranking ranking) {
        Integer points = fish.getLevel().getPoints();
        ranking.setScore(points + ranking.getScore());
        return rankingRepository.save(ranking);
    }
    /*public Ranking calculateScore(Fish fish, Ranking ranking, Member member, Competition competition) {
        Integer points = fish.getLevel().getPoints();
        return rankingRepository.save(
                Ranking.builder()
                        .id(RankId.builder()
                                .member_number(member.getNumber())
                                .competition_code(competition.getCode())
                                .build())
                        .member(member)
                        .competition(competition)
                        .raank(0)
                        .score(ranking.getScore() + points)
                        .build()
        );
    }*/

    @Override
    public List<Ranking> listScores(String codeCompetition) {
        competitionService.getCompetition(codeCompetition);
        List<Ranking> rankingList = rankingRepository.findAllByCompetition_CodeOrderByScoreDesc(codeCompetition);
        if(rankingList == null) throw new OperationException("No hunt inserted yet");
        int[] index = {0};
        List<Ranking> updatedList = rankingList.stream().map(ranking ->{
            ranking.setRaank(++index[0]);
            return ranking;
                }

        ).collect(Collectors.toList());
        rankingRepository.saveAll(updatedList);
        return updatedList;
    }
    /*public List<Ranking> listScores(String codeCompetition) {
        competitionService.getCompetition(codeCompetition);
        List<Ranking> rankingList = rankingRepository.findAllByCompetition_CodeOrderByScoreDesc(codeCompetition);
        if(rankingList == null) throw new OperationException("No hunt inserted yet");
        int[] index = {0};
        List<Ranking> updatedList = rankingList.stream().map(ranking ->
                Ranking.builder()
                        .id(RankId.builder()
                                .member_number(ranking.getMember().getNumber())
                                .competition_code(ranking.getCompetition().getCode())
                                .build())
                        .member(ranking.getMember())
                        .competition(ranking.getCompetition())
                        .raank(++index[0])
                        .score(ranking.getScore())
                        .build()
                ).toList();
        rankingRepository.saveAll(updatedList);
        return updatedList;
    }*/

    public boolean dateTimeComparison(LocalDate date, LocalTime time){
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime competitionDateTime = LocalDateTime.of(date, time);
        if (competitionDateTime.isBefore(currentDateTime)) return true;
        Duration duration = Duration.between(currentDateTime, competitionDateTime);
        return Math.abs(duration.getSeconds()) < 86400;
    }

}
