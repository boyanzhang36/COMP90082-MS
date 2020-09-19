package com.staxrt.tutorial.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


/**
 * The type User.
 *
 * @author Givantha Kalansuriya
 */
@Entity
@Table(name = "\"Resource\"")
@EntityListeners(AuditingEntityListener.class)
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private User uid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "website")
    private String website;

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

    public User getUid() {
        return uid;
    }


    /**
     * Sets id.
     *
     * @param uid the id
     */
    public void setUid(User uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets website.
     *
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;

    }
}