package com.example.lottopredictor.controller;

import com.example.lottopredictor.model.Regular;
import com.example.lottopredictor.scrapper.RegularScrapper;
import com.example.lottopredictor.service.RegularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RegularController {

    private final RegularScrapper regularScrapper;

    @Autowired
    RegularService regularService;

    public RegularController(RegularScrapper regularScrapper) {
        this.regularScrapper = regularScrapper;
    }

    @GetMapping("/regular")
    public ResponseEntity<List<Regular>> findAll() {
        return new ResponseEntity<>(regularService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/fetchRegular")
    public List<Regular> all() {
        return regularScrapper.fetchRegularData();
    }
}
