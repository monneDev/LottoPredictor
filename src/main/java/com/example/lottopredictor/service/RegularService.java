package com.example.lottopredictor.service;

import com.example.lottopredictor.model.Regular;
import com.example.lottopredictor.repository.RegularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegularService {

    @Autowired
    RegularRepository regularRepository;

    public List<Regular> findAll() {
        return regularRepository.findAll();
    }

    public void save(Regular regular) {
        regularRepository.save(regular);
    }
}
