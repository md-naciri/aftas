package com.example.aftas.service.Implementation;

import com.example.aftas.domain.Level;
import com.example.aftas.handler.OperationException;
import com.example.aftas.repository.LevelRepository;
import com.example.aftas.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LevelServiceImp implements LevelService {
    private final LevelRepository levelRepository;
    @Override
    public Level createLevel(Level level) {
        if (getLevel(level.getCode())) throw new OperationException("Level code already exists");
        checkLevelCodePoints(level);
        return levelRepository.save(level);
    }

    @Override
    public boolean getLevel(Integer levelCode) {
        Optional<Level> level = levelRepository.getLevelByCode(levelCode);
        return level.isPresent();
    }

    public void checkLevelCodePoints(Level newLevel){
        List<Level> levels = levelRepository.findAll();
        TreeMap<Integer, Integer> listLevels = new TreeMap<>();
        levels.forEach(level -> listLevels.put(level.getCode(), level.getPoints()));
        Integer newCode = newLevel.getCode();
        Integer newPoints = newLevel.getPoints();
        Integer lowerCode = listLevels.floorKey(newCode);
        Integer higherCode = listLevels.ceilingKey(newCode);
        if (!((lowerCode == null || newPoints > listLevels.get(lowerCode)) &&
                (higherCode == null || newPoints < listLevels.get(higherCode)))) throw new OperationException("The points entered for the level must match the code value");
    }

}
