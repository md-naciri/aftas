package com.example.aftas.service.Implementation;

import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Level;
import com.example.aftas.domain.Ranking;
import com.example.aftas.repository.RankingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RankingServiceImpTest {

    @Mock
    private Fish fish;

    @Mock
    private Ranking ranking;

    @Mock
    private RankingRepository rankingRepository;

    @InjectMocks
    private RankingServiceImp rankingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateScore() {

        Level level = new Level();
        level.setPoints(10);
        when(fish.getLevel()).thenReturn(level);
        when(ranking.getScore()).thenReturn(6);

        Ranking updatedRanking = new Ranking();
        updatedRanking.setScore(16);
        when(rankingRepository.save(any(Ranking.class))).thenReturn(updatedRanking);

        Ranking result = rankingService.calculateScore(fish, ranking);

        assertEquals(16, result.getScore());
        verify(rankingRepository).save(any(Ranking.class));
    }
}
