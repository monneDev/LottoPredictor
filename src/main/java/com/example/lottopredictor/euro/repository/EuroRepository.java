package com.example.lottopredictor.euro.repository;

import com.example.lottopredictor.euro.model.Euro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EuroRepository extends JpaRepository<Euro, Integer> {
}
