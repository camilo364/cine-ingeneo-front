package com.ingeneo.application.data.service;

import com.ingeneo.application.data.entity.Sala;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface SalaRepository extends JpaRepository<Sala, Integer> {

}