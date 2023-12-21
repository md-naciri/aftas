package com.example.aftas.service.Implementation;

import com.example.aftas.domain.Competition;
import com.example.aftas.handler.OperationException;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImp implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    @Override
    public Competition createCompetition(Competition competition) {
        Optional<Competition> competitionByDate = competitionRepository.findCompetitionByDate(competition.getDate());
        if (competitionByDate.isPresent()) throw new OperationException("You can't create more than one competitions with the same date, only one competition a day");
        if (competition.getEndTime().isBefore(competition.getStartTime())) throw new OperationException("The End Time should be after the Start Time");
        if (competition.getDate().isBefore(LocalDate.now()) || competition.getDate().isEqual(LocalDate.now())) throw new OperationException("You can't create a competition in the past or on the same day");
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

    @Override
    public Page<Competition> getCompetitionsPagination(Pageable pageable) {
        return  competitionRepository.findAll(pageable);
    }

    @Override
    public List<Competition> getCompetitions() {
        return  competitionRepository.findAll();
    }

    @Override
    public List<Competition> filterCompetitionsInProgress() {
        List<Competition> all = competitionRepository.findAll();
        List<Competition> filterInProgress = new ArrayList<>();
        for (Competition competition : all) {
            LocalDateTime competitionEnd = LocalDateTime.of(competition.getDate(), competition.getEndTime());
            LocalDateTime todayDateTime = LocalDateTime.now();
            if (todayDateTime.isBefore(competitionEnd)) {
                filterInProgress.add(competition);
            }
        }
        return filterInProgress;
    }

    @Override
    public List<Competition> filterCompetitionsClosed() {
        List<Competition> all = competitionRepository.findAll();
        List<Competition> filterClosed = new ArrayList<>();
        for (Competition competition : all) {
            LocalDateTime competitionEnd = LocalDateTime.of(competition.getDate(), competition.getStartTime());
            LocalDateTime todayDateTime = LocalDateTime.now();
            if (todayDateTime.isAfter(competitionEnd)) {
                filterClosed.add(competition);
            }
        }
        return filterClosed;
    }

}
