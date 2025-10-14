package com.example.lottopredictor.regular.service;

import com.example.lottopredictor.regular.model.Regular;
import com.example.lottopredictor.regular.repository.RegularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegularService {

    private final RegularRepository regularRepository;

    public RegularService(RegularRepository regularRepository) {
        this.regularRepository = regularRepository;
    }

    public List<Regular> findAll() {
        return regularRepository.findAll();
    }

    public void save(Regular regular) {
        regularRepository.save(regular);
    }
}
