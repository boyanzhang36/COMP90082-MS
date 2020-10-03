package com.staxrt.tutorial.controller;


import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.NotificationToken;
import com.staxrt.tutorial.repository.NotificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class NotificationTokenController {

    @Autowired
    private NotificationTokenRepository notificationTokenRepository;

    // Get all notification TOkens 
    @GetMapping("/notificationTokens")
    public List<NotificationToken> getAllNT() { return notificationTokenRepository.findAll(); }

    // Get a specific notification token 
    @GetMapping("/notificationTokens/{id}")
    public ResponseEntity<NotificationToken> getNTById(@PathVariable("id") int id) throws ResourceNotFoundException {
        NotificationToken notificationToken = notificationTokenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found on: " + id));
        return ResponseEntity.ok().body(notificationToken);
    }

    // Create a notification token 
    @PostMapping("/notificationTokens")
    public NotificationToken createNT(@Valid @RequestBody NotificationToken notificationToken) {
        return notificationTokenRepository.save(notificationToken);
    }

    // Update a notification token 
    @PutMapping("/notificationTokens/{id}")
    public ResponseEntity<NotificationToken> updateNT(@PathVariable("id") int id,
                                                        @Valid @RequestBody NotificationToken newNotificationToken)
            throws ResourceNotFoundException {

        NotificationToken nToken = notificationTokenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found on: " + id));

        nToken.setId(newNotificationToken.getId());
        nToken.setUid(newNotificationToken.getUid());
        nToken.setFcm_token((newNotificationToken.getFcm_token()));

        final NotificationToken updatedNT = notificationTokenRepository.save(nToken);
        return ResponseEntity.ok(updatedNT);
    }

    // Delete a notification token 
    @DeleteMapping("/notificationTokens/{id}")
    public Map<String, Boolean> deleteNT(@PathVariable("id") int id) throws Exception {
        NotificationToken nToken = notificationTokenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found on: " + id));

        notificationTokenRepository.delete(nToken);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
