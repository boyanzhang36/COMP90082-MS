package com.staxrt.tutorial.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

/**
 * The type User.
 *
 * @author Givantha Kalansuriya
 */
@Entity
@Table(name = "\"Radiology\"")
@EntityListeners(AuditingEntityListener.class)
public class Radiology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 2, message = "Name should have atleast 2 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "address", nullable = true)
    private String address;

    @NotNull
    @Column(name = "phone", nullable = true)
    private String phone;


    @NotNull
    @Column(name = "fax", nullable = true)
    private String fax;

    @NotNull
    @Column(name = "hours", nullable = true)
    private String hours;

    @NotNull
    @Email
    @Column(name = "email", nullable = true)
    private String email;

    @NotNull
    @Column(name = "website", nullable = true)
    private String website;


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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;

    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getFax() {
        return fax;
    }


    /**
     * Sets first name.
     *
     * @param firstName the first name
     */


    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getHours() {
        return hours;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail(){
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets phone.
     *
     * @return the email
     */
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


}
