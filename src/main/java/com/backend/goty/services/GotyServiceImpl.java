package com.backend.goty.services;

import com.backend.goty.entities.Goty;
import com.backend.goty.repositories.GotyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GotyServiceImpl implements GotyService{

    @Autowired
    GotyRepository gotyRepositoryObj;


    @Override
    public List<Goty> findAll() {
        return gotyRepositoryObj.findAll();
    }

    @Override
    public Goty findById(Long id) {
        return gotyRepositoryObj.findById(id).orElse(null);
    }

    @Override
    public Goty save(Goty goty) {
        return gotyRepositoryObj.save(goty);
    }
}
