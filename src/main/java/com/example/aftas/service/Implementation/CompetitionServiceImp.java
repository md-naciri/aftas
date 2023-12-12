package com.example.aftas.service.Implementation;

import com.example.aftas.domain.Competition;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImp implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    @Override
    public Competition createCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    @Override
    public Competition getCompetition(String code) {
        Optional<Competition> competition = competitionRepository.findById(code);
        if (competition.isEmpty()){
            throw new  IllegalArgumentException("Competition doesn't exist");
        }
        return competition.get();
    }
}
