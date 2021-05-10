package com.backend.goty.services;

import com.backend.goty.entities.Goty;

import java.util.List;


public interface GotyService {

    List<Goty> findAll();
    Goty findById(Long id);
    Goty save(Goty goty);
}
