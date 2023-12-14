package com.example.aftas.service;
import com.example.aftas.domain.Fish;

public interface FishService {
    Fish getFish(String fishName);
    Fish createFish(Fish fish);
}
