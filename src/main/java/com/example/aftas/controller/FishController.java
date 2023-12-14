package com.example.aftas.controller;

import com.example.aftas.VM.FishRequestVM;
import com.example.aftas.domain.Fish;
import com.example.aftas.service.FishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FishController {
    private final FishService fishService;
    @PostMapping
    public ResponseEntity<?> createFish(@RequestBody @Valid FishRequestVM fishRequestVM){

        return null;
    }
}
