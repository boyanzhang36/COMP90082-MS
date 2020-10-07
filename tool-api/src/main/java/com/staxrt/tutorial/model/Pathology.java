package com.staxrt.tutorial.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Native;

@Entity
@Table(name = "\"Pathology\"")
@EntityListeners(AuditingEntityListener.class)
public class Pathology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @NotNull
    @Size(min = 2, message = "Name should have atleast 2 characters")
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull
    @Column(name = "address", nullable = false)
    private String address;
    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "hours")
    private String hours;

    @Column(name = "website")
    private String website;


    ////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
