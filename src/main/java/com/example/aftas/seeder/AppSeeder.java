package com.example.aftas.seeder;

import com.example.aftas.seeder.dbSeeders.FishSeeder;
import com.example.aftas.seeder.dbSeeders.LevelSeeder;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AppSeeder {
    private final LevelSeeder levelSeeder;
    private final FishSeeder fishSeeder;

    public AppSeeder(LevelSeeder levelSeeder, FishSeeder fishSeeder) {
        this.levelSeeder = levelSeeder;
        this.fishSeeder = fishSeeder;
    }

    @PostConstruct
    public void init(){
        levelSeeder.seed();
        fishSeeder.seed();
    }
}
