package com.backend.goty.repositories;

import com.backend.goty.entities.Goty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GotyRepository extends JpaRepository<Goty, Long> {
}
