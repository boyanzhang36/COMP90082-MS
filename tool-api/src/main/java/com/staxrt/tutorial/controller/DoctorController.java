/*
 *
 *  Copyright (c) 2018-2020 Givantha Kalansuriya, This source is a part of
 *   Staxrt - sample application source code.
 *   http://staxrt.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Doctor;
import com.staxrt.tutorial.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpMethod;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class DoctorController {

  @Autowired
  private DoctorRepository doctorRepository;

  /**
   * Get all users list.
   *
   * @return the list
   */

  @GetMapping("/doctors")
  public List<Doctor> getAllUsers() {
    return doctorRepository.findAll();
  }

    /**
   * Create user user.
   *
   * @param user the user
   * @return the user
   */

     @RequestMapping(value="/doctors", method = RequestMethod.OPTIONS)
     ResponseEntity<?> collectionOptions() 
     {
          return ResponseEntity
                  .ok()
                  .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS)
                  .build();
     }
      
     @RequestMapping(value="/doctors/{id}", method = RequestMethod.OPTIONS)
     ResponseEntity<?> singularOptions() 
     {
          return ResponseEntity
                  .ok()
                  .allow(HttpMethod.GET, HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.OPTIONS)
                  .build();
     }

  @PostMapping("/doctors")
  public Doctor createDoctor(@Valid @RequestBody Doctor doctor) {
    return doctorRepository.save(doctor);
  }

  @GetMapping("/doctors/{id}")
  public ResponseEntity<Doctor> getDoctorsById(@PathVariable(value = "id") int doctorId)
      throws ResourceNotFoundException {
    Doctor doctor =
        doctorRepository
            .findById(doctorId)
            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found on :: " + doctorId));
    return ResponseEntity.ok().body(doctor);
  }

   /**
   * Update user response entity.
   *
   * @param doctorId the user id
   * @return the response entity
   * @throws ResourceNotFoundException the resource not found exception
   */
  @PutMapping("/doctors/{id}")
  public ResponseEntity<Doctor> updateDoctor(
      @PathVariable(value = "id") Integer doctorId, @Valid @RequestBody Doctor doctorDetails)
      throws ResourceNotFoundException {

    Doctor doctor =
        doctorRepository
            .findById(doctorId)
            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found on :: " + doctorId));

    doctor.setName(doctorDetails.getName());
    doctor.setBio(doctorDetails.getBio());
    doctor.setAddress(doctorDetails.getAddress());
    doctor.setPhone(doctorDetails.getPhone());
    doctor.setFax(doctorDetails.getFax());
    doctor.setEmail(doctorDetails.getEmail());
    doctor.setWebsite(doctorDetails.getWebsite());
    doctor.setExpertise(doctorDetails.getExpertise());


    final Doctor updatedDoctor = doctorRepository.save(doctor);
    return ResponseEntity.ok(updatedDoctor);
  }

  @DeleteMapping("/doctors/{id}")
  public Map<String, Boolean> deleteDoctor(@PathVariable(value = "id") Integer doctorId) throws Exception {
    Doctor doctor =
        doctorRepository
            .findById(doctorId)
            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found on : " + doctorId));

    doctorRepository.delete(doctor);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }


}
