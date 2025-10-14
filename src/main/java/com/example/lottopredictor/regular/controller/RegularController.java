package com.example.lottopredictor.regular.controller;

import com.example.lottopredictor.regular.model.Regular;
import com.example.lottopredictor.regular.scrapper.RegularScrapper;
import com.example.lottopredictor.regular.service.RegularService;
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
    private final RegularService regularService;

    public RegularController(RegularScrapper regularScrapper, RegularService regularService) {
        this.regularScrapper = regularScrapper;
        this.regularService = regularService;
    }

    @GetMapping("/regulars")
    public ResponseEntity<List<Regular>> findAll() {
        return new ResponseEntity<>(regularService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/fetchRegular")
    public List<Regular> all() {
        return regularScrapper.fetchRegularData();
    }
}
