package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.Doctor;
import com.staxrt.tutorial.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {}
