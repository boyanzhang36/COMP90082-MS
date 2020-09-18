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

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
  @PostMapping("/doctors")
  public Doctor createDoctor(@Valid @RequestBody Doctor doctor) {
    return doctorRepository.save(doctor);
  }


}
