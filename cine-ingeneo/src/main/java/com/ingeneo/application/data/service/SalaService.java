package com.ingeneo.application.data.service;

import com.ingeneo.application.data.entity.Sala;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import java.time.LocalDate;

@Service
public class SalaService extends CrudService<Sala, Integer> {

    private SalaRepository repository;

    public SalaService(@Autowired SalaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected SalaRepository getRepository() {
        return repository;
    }

}
