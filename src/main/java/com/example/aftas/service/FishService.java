package com.example.aftas.service;
import com.example.aftas.VM.FishRequestVM;
import com.example.aftas.domain.Fish;

public interface FishService {
    Fish getFish(String fishName);
    Fish createFish(FishRequestVM fishRequestVM);
}
