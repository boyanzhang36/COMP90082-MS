package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Doctor;
import com.staxrt.tutorial.model.Radiology;
import com.staxrt.tutorial.model.Resource;
import com.staxrt.tutorial.model.User;
import com.staxrt.tutorial.repository.DoctorRepository;
import com.staxrt.tutorial.repository.RadiologyRepository;
import com.staxrt.tutorial.repository.ResourceRepository;
import com.staxrt.tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class RadiologyController {

    @Autowired
    private RadiologyRepository radiologyRepository;

    /**
     * Get all users list.
     *
     * @return the list
     */
    @GetMapping("/radiologies")
    public List<Radiology> getAllRadiologies() {
        return radiologyRepository.findAll();
    }

    /**
     * Create user user.
     *
     * @param resource the user
     * @return the user
     */
    @PostMapping("/radiologies")
    public Radiology createRadiology(@Valid @RequestBody Radiology radiology) {
        return radiologyRepository.save(radiology);
    }

    @GetMapping("/radiologies/{id}")
    public ResponseEntity<Radiology> getRadiologiesById(@PathVariable(value = "id") int radiologyId)
            throws ResourceNotFoundException {
        Radiology radiology =
                radiologyRepository
                        .findById(radiologyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Radiology not found on :: " + radiologyId));
        return ResponseEntity.ok().body(radiology);
    }

    /**
     * Update user response entity.
     *
     * @param resourceId the user id
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/radiologies/{id}")
    public ResponseEntity<Radiology> updateRadiology(
            @PathVariable(value = "id") int radiologyId, @Valid @RequestBody Radiology radiologyDetails)
            throws ResourceNotFoundException {

        Radiology radiology =
                radiologyRepository
                        .findById(radiologyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Radiology not found on :: " + radiologyId));

        radiology.setName(radiologyDetails.getName());
        radiology.setAddress(radiologyDetails.getAddress());
        radiology.setPhone(radiologyDetails.getPhone());
        radiology.setFax(radiologyDetails.getFax());
        radiology.setHours(radiologyDetails.getHours());
        radiology.setEmail(radiologyDetails.getEmail());
        radiology.setWebsite(radiologyDetails.getWebsite());


        final Radiology updatedRadiology = radiologyRepository.save(radiology);
        return ResponseEntity.ok(updatedRadiology);
    }

    @DeleteMapping("/radiologies/{id}")
    public Map<String, Boolean> deleteRadiology(@PathVariable(value = "id") Integer radiologyId) throws Exception {
        Radiology radiology =
                radiologyRepository
                        .findById(radiologyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Radiology not found on :: " + radiologyId));

        radiologyRepository.delete(radiology);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
