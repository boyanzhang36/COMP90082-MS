package com.staxrt.tutorial.controller;


import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Appointment;
import com.staxrt.tutorial.model.Hospital;
import com.staxrt.tutorial.repository.AppointmentRepository;
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
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;


    // Get all Users
    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Get a User
    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") int id)
            throws ResourceNotFoundException {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found on: " + id));

        return ResponseEntity.ok().body(appointment);
    }

    // Post a Appointment 
    @PostMapping("/appointments")
    public Appointment createAppointment(@Valid @RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Update a Appointment 
    @PutMapping("/appointments/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable("id") int id,
                                                   @Valid @RequestBody Appointment newAppointment)
            throws ResourceNotFoundException {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found on: " + id));

        appointment.setUid(newAppointment.getUid());
        appointment.setDid(newAppointment.getDid());
        appointment.setTitle(newAppointment.getTitle());
        appointment.setDetail(newAppointment.getDetail());
        appointment.setDate_create(newAppointment.getDate_create());
        appointment.setDate_change(newAppointment.getDate_change());
        appointment.setDate(newAppointment.getDate());
        appointment.setDuration(newAppointment.getDuration());
        appointment.setNote(newAppointment.getNote());
        appointment.setUser_note(newAppointment.getUser_note());
        appointment.setStatus(newAppointment.getStatus());

        final Appointment updatedAppointment = appointmentRepository.save(appointment);
        return ResponseEntity.ok(updatedAppointment);
    }


    // Delete an Appointment 
    @DeleteMapping("/appointments/{id}")
    public Map<String, Boolean> deleteAppointment(@PathVariable("id") int id) throws Exception {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found on: " + id));

        appointmentRepository.delete(appointment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
