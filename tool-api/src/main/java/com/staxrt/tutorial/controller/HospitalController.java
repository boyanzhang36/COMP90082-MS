package com.staxrt.tutorial.controller;


import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Doctor;
import com.staxrt.tutorial.model.Hospital;
import com.staxrt.tutorial.repository.HospitalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HospitalController {

    @Autowired
    private HospitalRepository hospitalRepository;

    @GetMapping("/hospitals")
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    @GetMapping("/hospitals/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable("id") int id) throws ResourceNotFoundException {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found on: " + id));
        return ResponseEntity.ok().body(hospital);
    }

    @PostMapping("/hospitals")
    public Hospital createHospital(@Valid @RequestBody Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    @PutMapping("/hospitals/{id}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable("id") int id,
                                                   @Valid @RequestBody Hospital newHospital)
            throws ResourceNotFoundException {

        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found on: " + id));

        hospital.setName(newHospital.getName());
        hospital.setAddress(newHospital.getAddress());
        hospital.setPhone(newHospital.getPhone());
        hospital.setAftPhone(newHospital.getAftPhone());
        hospital.setEmail(newHospital.getEmail());
        hospital.setFax(newHospital.getFax());
        hospital.setWebsite(newHospital.getWebsite());
        hospital.setEmergencyDept(newHospital.getEmergencyDept());

        final Hospital updatedHospital = hospitalRepository.save(hospital);
        return ResponseEntity.ok(updatedHospital);
    }

    @DeleteMapping("/hospitals/{id}")
    public Map<String, Boolean> deleteHospical(@PathVariable("id") int id) throws Exception {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found on: " + id));

        hospitalRepository.delete(hospital);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
