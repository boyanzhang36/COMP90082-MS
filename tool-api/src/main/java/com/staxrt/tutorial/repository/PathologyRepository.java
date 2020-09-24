package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.Pathology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathologyRepository extends JpaRepository<Pathology, Integer> {
}
