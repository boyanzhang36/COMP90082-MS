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

package com.staxrt.tutorial.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;

/**
 * The type User.
 *
 * @author Givantha Kalansuriya
 */
@Entity
@Table(name = "\"Doctor\"")
@EntityListeners(AuditingEntityListener.class)
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "bio")
    private String bio;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "website")
    private String website;
    
    @Column(name = "expertise")
    private String expertise;

    

  /**
   * Gets id.
   *
   * @return the id
   */
  public int getId() {
        return id;
    }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(int id) {
        this.id = id;
    }

  /**
   * Gets first name.
   *
   * @return the first name
   */
  public String getName() {
        return name;
    }


  /**
   * Sets first name.
   *
   * @param firstName the first name
   */
  public void setName(String name) {
        this.name = name;
    }

  /**
   * Gets last name.
   *
   * @return the last name
   */
  public String getBio() {
        return bio;
    }

  /**
   * Sets last name.
   *
   * @param lastName the last name
   */
  public void setBio(String bio) {
        this.bio = bio;
    }

  /**
   * Gets email.
   *
   * @return the email
   */
  public String getAddress() {
        return address;
    }

  /**
   * Sets email.
   *
   * @param email the email
   */
  public void setAddress(String address) {
        this.address = address;
    }

  /**
   * Gets phone.
   *
   * @return the email
   */
  public String getPhone() {
        return phone;
    }

  /**
   * Sets phone.
   *
   * @param email the phone
   */
  public void setPhone(String phone) {
        this.phone = phone;
    }

      /**
   * Gets fax.
   *
   * @return the fax
   */
  public String getFax() {
        return fax;
    }

  /**
   * Sets phone.
   *
   * @param email the phone
   */
  public void setFax(String fax) {
        this.fax = fax;
    }

          /**
   * Gets fax.
   *
   * @return the fax
   */
  public String getEmail() {
        return email;
    }

  /**
   * Sets phone.
   *
   * @param email the phone
   */
  public void setEmail(String email) {
        this.email = email;
    }

  public String getWebsite() {
        return website;
    }

  /**
   * Sets phone.
   *
   * @param email the phone
   */
  public void setWebsite(String website) {
        this.website = website;
    }

  public String getExpertise() {
        return expertise;
    }

  /**
   * Sets phone.
   *
   * @param email the phone
   */
  public void setExpertise(String expertise) {
        this.expertise = expertise;
    }


    @Override
    public String toString() {
        return address;
    }


}
