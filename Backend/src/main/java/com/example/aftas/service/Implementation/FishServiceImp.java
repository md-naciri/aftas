package com.example.aftas.service.Implementation;

import com.example.aftas.VM.FishRequestVM;
import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Level;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.service.FishService;
import com.example.aftas.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FishServiceImp implements FishService {
    private final FishRepository fishRepository;
    private final LevelService levelService;
    @Override
    public Fish getFish(String fishName) {
        Optional<Fish> fish = fishRepository.findByName(fishName);
        if(fish.isEmpty()) throw new IllegalArgumentException("Fish doesn't exist");
        return fish.get();
    }

    @Override
    public Fish createFish(FishRequestVM fishRequestVM) {
        Optional<Fish> isFishExist = fishRepository.findByName(fishRequestVM.name());
        if(isFishExist.isPresent()) throw new IllegalArgumentException("Fish already exists");
        Level level = levelService.getLevel(fishRequestVM.levelCode());
        return fishRepository.save(
                Fish.builder()
                .name(fishRequestVM.name())
                .averageWeight(fishRequestVM.averageWeight())
                .level(level)
                .build());
    }
}
