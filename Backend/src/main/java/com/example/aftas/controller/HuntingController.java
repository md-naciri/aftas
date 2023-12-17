package com.example.aftas.controller;

import com.example.aftas.VM.HuntingRequestVM;
import com.example.aftas.VM.HuntingResponseVM;
import com.example.aftas.domain.Hunting;
import com.example.aftas.domain.Ranking;
import com.example.aftas.handler.ResponseHandler;
import com.example.aftas.service.HuntingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aftas/api/v1/hunting")
@RequiredArgsConstructor
public class HuntingController {
    private final HuntingService huntingService;
    @PostMapping
    public ResponseEntity<?> insertHunting(@RequestBody @Valid HuntingRequestVM huntingRequestVM){
        Hunting hunting = huntingService.createHunting(huntingRequestVM.toHunting());
        HuntingResponseVM huntingResponseVM = HuntingResponseVM.huntingResponseVM(hunting);
        return ResponseHandler.created(huntingResponseVM, "Hunting inserted successfully");
    }
}
