package com.example.aftas.service;

import com.example.aftas.domain.Hunting;

public interface HuntingService {
    Hunting createHunting(Long memberNumber, String competitionCode, String fishName, Double fishWeight);

}
