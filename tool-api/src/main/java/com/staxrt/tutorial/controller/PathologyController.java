package com.staxrt.tutorial.controller;


import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Pathology;
import com.staxrt.tutorial.repository.PathologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PathologyController {
    @Autowired
    private PathologyRepository pathologyRepository;

    @GetMapping("pathologies")
    public List<Pathology> getAllPathologies() {return pathologyRepository.findAll();}

    @GetMapping("pathologies/{id}")
    public ResponseEntity<Pathology> getPathologyById(@PathVariable("id")int id) throws ResourceNotFoundException {
        Pathology pathology = pathologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pathology not found on: "+ id));
        return ResponseEntity.ok().body(pathology);
    }

    @PostMapping("pathologies")
      public Pathology createPathology(@Valid @RequestBody Pathology pathology) {
            return pathologyRepository.save(pathology);
      }


    @PutMapping("pathologies/{id}")
    public ResponseEntity<Pathology> updatePathologyById(@PathVariable("id") int id,
                                                         @Valid @RequestBody Pathology newPathology)
            throws ResourceNotFoundException {

        Pathology pathology = pathologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pathology not found on: "+ id));

        pathology.setName(newPathology.getName());
        pathology.setAddress(newPathology.getAddress());
        pathology.setPhone(newPathology.getPhone());
        pathology.setWebsite(newPathology.getWebsite());
        pathology.setHours(newPathology.getHours());

        final Pathology updatedPathology = pathologyRepository.save(pathology);
        return ResponseEntity.ok(updatedPathology);
    }

    @DeleteMapping("pathologies/{id}")
    public Map<String, Boolean> deletePathology(@PathVariable("id") int id) throws Exception {
        Pathology pathology = pathologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pathology not found on: "+ id));

        pathologyRepository.delete(pathology);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

