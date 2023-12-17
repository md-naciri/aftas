package com.example.aftas.service;

import com.example.aftas.domain.Level;

public interface LevelService {
    Level createLevel(Level level);
    boolean isLevelExist(Integer levelCode);
    Level getLevel(Integer levelCode);
}
