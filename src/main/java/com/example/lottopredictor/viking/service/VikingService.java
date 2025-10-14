package com.example.lottopredictor.viking.service;

import com.example.lottopredictor.viking.model.Viking;
import com.example.lottopredictor.viking.repository.VikingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VikingService {

    private final VikingRepository vikingRepository;

    public VikingService(VikingRepository vikingRepository) {
        this.vikingRepository = vikingRepository;
    }

    public List<Viking> findAll() {
        return vikingRepository.findAll();
    }

    public void save(Viking viking) {
        vikingRepository.save(viking);
    }
}
