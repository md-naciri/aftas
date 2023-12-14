package com.example.aftas.service.Implementation;

import com.example.aftas.domain.Fish;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.service.FishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FishServiceImp implements FishService {
    private final FishRepository fishRepository;
    @Override
    public Fish getFish(String fishName) {
        Optional<Fish> fish = fishRepository.findByName(fishName);
        if(fish.isEmpty()) throw new IllegalArgumentException("Fish doesn't exist");
        return fish.get();
    }

    @Override
    public Fish createFish(Fish fish) {
        Optional<Fish> isFishExist = fishRepository.findByName(fish.getName());
        if(isFishExist.isPresent()) throw new IllegalArgumentException("Fish already exists");
        return fishRepository.save(fish);
    }
}
