package com.example.lottopredictor.euro.service;

import com.example.lottopredictor.euro.model.Euro;
import com.example.lottopredictor.euro.repository.EuroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EuroService {

    private final EuroRepository euroRepository;

    public EuroService(EuroRepository euroRepository) {
        this.euroRepository = euroRepository;
    }

    public List<Euro> findAll() {
        return euroRepository.findAll();
    }

    public void save(Euro euro) {
        euroRepository.save(euro);
    }
}
