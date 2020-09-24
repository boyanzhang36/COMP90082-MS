package com.staxrt.tutorial.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "\"File\"")
@EntityListeners(AuditingEntityListener.class)
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "apptid", nullable = false)
    private int apptid;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "link", nullable = false)
    private String link;


    //////////////////////


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApptid() {
        return apptid;
    }

    public void setApptid(int apptid) {
        this.apptid = apptid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
