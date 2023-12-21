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
        // Arrange
        Level level = new Level(); // Assuming Level class exists and has a setPoints method
        level.setPoints(10);
        when(fish.getLevel()).thenReturn(level);
        when(ranking.getScore()).thenReturn(6);

        Ranking updatedRanking = new Ranking();
        updatedRanking.setScore(fish.getLevel().getPoints() + ranking.getScore()); // Expected score is 10 (from fish) + 5 (from ranking)
        when(rankingRepository.save(any(Ranking.class))).thenReturn(updatedRanking);

        // Act
        Ranking result = rankingService.calculateScore(fish, ranking);

        // Assert
        assertEquals(16, result.getScore());
        verify(rankingRepository).save(any(Ranking.class));
    }
}
