package com.example.aftas.service.Implementation;

import com.example.aftas.domain.Competition;
import com.example.aftas.handler.OperationException;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImp implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    @Override
    public Competition createCompetition(Competition competition) {
        Optional<Competition> competitionByDate = competitionRepository.findCompetitionByDate(competition.getDate());
        if (competitionByDate.isPresent()){
            throw new OperationException("You can't create more than one competitions with the same date, only one competition a day");
        }
        //startdate>enddate
        competition.setCode(generateCode(competition.getLocation(), competition.getDate()));
        return competitionRepository.save(competition);
    }

    private String generateCode(String location, LocalDate date){
        String trimmedLocation = location.length() > 3 ? location.substring(0, 3) : location;
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
        return trimmedLocation.toLowerCase() + "-" + formattedDate;
    }

    @Override
    public Competition getCompetition(String code) {
        Optional<Competition> competition = competitionRepository.findById(code);
        if (competition.isEmpty()){
            throw new  IllegalArgumentException("Competition doesn't exist");
        }
        return competition.get();
    }

    @Override
    public Competition updateCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

}
