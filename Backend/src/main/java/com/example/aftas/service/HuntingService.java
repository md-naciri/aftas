package com.example.aftas.service;

import com.example.aftas.domain.Hunting;

public interface HuntingService {
    Hunting createHunting(Hunting hunting);
    Hunting doesMemberHuntFish (Long memberNumber, String competitionCode, String fishName);
}
