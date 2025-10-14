package com.example.lottopredictor.viking.controller;

import com.example.lottopredictor.viking.model.Viking;
import com.example.lottopredictor.viking.scrapper.VikingScrapper;
import com.example.lottopredictor.viking.service.VikingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VikingController {

    private final VikingScrapper vikingScrapper;
    private final VikingService vikingService;

    public VikingController(VikingScrapper vikingScrapper, VikingService vikingService) {
        this.vikingScrapper = vikingScrapper;
        this.vikingService = vikingService;
    }

    @GetMapping("/vikings")
    public ResponseEntity<List<Viking>> findAll() {
        return new ResponseEntity<>(vikingService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/fetchViking")
    public List<Viking> all() {
        return vikingScrapper.fetchVikingData();
    }
}
