package com.example.lottopredictor.euro.controller;

import com.example.lottopredictor.euro.model.Euro;
import com.example.lottopredictor.euro.scrapper.EuroScrapper;
import com.example.lottopredictor.euro.service.EuroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EuroController {

    private final EuroScrapper euroScrapper;
    private final EuroService euroService;

    public EuroController(EuroScrapper euroScrapper, EuroService euroService) {
        this.euroScrapper = euroScrapper;
        this.euroService = euroService;
    }

    @GetMapping("/euros")
    public ResponseEntity<List<Euro>> findAll() {
        return new ResponseEntity<>(euroService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/fetchEuro")
    public List<Euro> all() {
        return euroScrapper.fetchEuroData();
    }
}
