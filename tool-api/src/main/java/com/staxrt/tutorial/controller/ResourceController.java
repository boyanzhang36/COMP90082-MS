package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Resource;
import com.staxrt.tutorial.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class ResourceController {

    @Autowired
    private ResourceRepository resourceRepository;

    // Get all resources 
    @GetMapping("/resources")
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }


    // Create a resource 
    @PostMapping("/resources")
    public Resource createResource(@Valid @RequestBody Resource resource) {
        return resourceRepository.save(resource);
    }

    // Get a specific resource 
    @GetMapping("/resources/{id}")
    public ResponseEntity<Resource> getResourcesById(@PathVariable(value = "id") int resourceId)
            throws ResourceNotFoundException {
        Resource resource =
                resourceRepository
                        .findById(resourceId)
                        .orElseThrow(() -> new ResourceNotFoundException("Resource not found on :: " + resourceId));
        return ResponseEntity.ok().body(resource);
    }

    // Update a resource 
    @PutMapping("/resources/{id}")
    public ResponseEntity<Resource> updateResource(
            @PathVariable(value = "id") int resourceId, @Valid @RequestBody Resource resourceDetails)
            throws ResourceNotFoundException {

        Resource resource =
                resourceRepository
                        .findById(resourceId)
                        .orElseThrow(() -> new ResourceNotFoundException("Resource not found on :: " + resourceId));

        resource.setUid(resourceDetails.getUid());
        resource.setName(resourceDetails.getName());
        resource.setWebsite(resourceDetails.getWebsite());





        final Resource updatedResource = resourceRepository.save(resource);
        return ResponseEntity.ok(updatedResource);
    }

    // Delete a resource 
    @DeleteMapping("/resources/{id}")
    public Map<String, Boolean> deleteResource(@PathVariable(value = "id") Integer resourceId) throws Exception {
        Resource resource =
                resourceRepository
                        .findById(resourceId)
                        .orElseThrow(() -> new ResourceNotFoundException("Resource not found on :: " + resourceId));

        resourceRepository.delete(resource);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
