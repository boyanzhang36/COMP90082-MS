package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.Radiology;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 *
 * @author Givantha Kalansuriya
 */
@Repository
public interface RadiologyRepository extends JpaRepository<Radiology, Integer> {}
