package com.example.aftas.service;

import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Hunting;

import java.util.Optional;

public interface FishService {
    Fish getFish(String fishName);
}
