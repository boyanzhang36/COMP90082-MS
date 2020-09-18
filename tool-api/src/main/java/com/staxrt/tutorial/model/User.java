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
@Table(name = "\"User\"")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "middlename", nullable = true)
    private String middlename;


    @Column(name = "password", nullable = true)
    private String password;


    @DateTimeFormat
    @Column(name = "dob", nullable = false)
    private Date dob;

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
    @Column(name = "token_expire_date", nullable = false)
    private Date token_expire_date;

    @Enumerated(EnumType.STRING)
    @Column(name ="role")
    private Role role;


    public enum Role{
        PATIENT,
        ADMIN

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
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;

    }
    public String getMiddlename() {
        return middlename;
    }
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }
    public String getSurname() {
        return surname;
    }


    /**
     * Sets first name.
     *
     * @param firstName the first name
     */


    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public Date getDOB() {
        return dob;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setDOB(Date dob) {
        this.dob = dob;
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
    public String getToken()
    {return token;}
    public void setToken(String token)
    {this.token=token;}
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
    public Date getTokenexpiredate() {
        return token_expire_date;
    }

    /**
     * Sets phone.
     *
     * @param email the phone
     */
    public void setTokenexpiredate(Date token_expire_date) {
        this.token_expire_date = token_expire_date;
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
