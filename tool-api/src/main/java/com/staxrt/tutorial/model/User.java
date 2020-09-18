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

import java.util.Date;

/**
 * The type User.
 *
 * @author Givantha Kalansuriya
 */
@Entity
@Table(name = "\" User\"")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "middle_name", nullable = true)
    private String middle_name;


    @Column(name = "password", nullable = true)
    private String password;


    @DateTimeFormat
    @Column(name = "DOB", nullable = false)
    private Date DOB;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "street", nullable = true)
    private String street;

    @Column(name = "suburb", nullable = true)
    private String suburb;
    @Column(name = "state", nullable = true)
    private String state;
    @Column(name = "token", nullable = true)
    private String token;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "token_valid_from", nullable = false)
    private Date token_valid_from;

    @UpdateTimestamp
    @Column(name = "token_expiry_date", nullable = false)
    private Date token_expiry_date;

    @Enumerated(EnumType.STRING)
    @Column(name ="role")
    private Role role;


    public enum Role{
        ADMIN;

    }



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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstname() {
        return first_name;
    }
    public void setFirstname(String first_name) {
        this.first_name = first_name;

    }
    public String getMiddlename() {
        return middle_name;
    }
    public void setMiddlename(String middle_name) {
        this.middle_name = middle_name;
    }
    public String getLastname() {
        return last_name;
    }


    /**
     * Sets first name.
     *
     * @param firstName the first name
     */


    public void setLastname(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public Date getDOB() {
        return DOB;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setDOB(Date DOB) {
        this.DOB = DOB;
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
    public String getStreet() {
        return street;
    }

    /**
     * Sets phone.
     *
     * @param email the phone
     */
    public void setStreet(String street) {
        this.street = street;
    }
    public String getSuburb() {
            return suburb;
     }

        /**
         * Sets phone.
         *
         * @param email the phone
         */
    public void setSuburb(String suburb) {
            this.suburb = suburb;
        }
    public String getState() {
            return state;
        }

        /**
         * Sets phone.
         *
         * @param email the phone
         */
    public void setState(String state) {
            this.state = state;
        }

    /**
     * Gets fax.
     *
     * @return the fax
     */
    public Date getTokenvalidfrom() {
        return token_valid_from;
    }

    /**
     * Sets phone.
     *
     * @param email the phone
     */
    public void setTokenvalidfrom(Date token_valid_from) {
        this.token_valid_from = token_valid_from;
    }

    /**
     * Gets fax.
     *
     * @return the fax
     */
    public Date getTokenexpirydate() {
        return token_expiry_date;
    }

    /**
     * Sets phone.
     *
     * @param email the phone
     */
    public void setTokenexpirydate(Date token_expiry_date) {
        this.token_expiry_date = token_expiry_date;
    }

    public Role getRole() {
        return role;
    }

    /**
     * Sets phone.
     *
     * @param email the phone
     */
    public void setRole(Role role) {
        this.role = role;
    }



}
