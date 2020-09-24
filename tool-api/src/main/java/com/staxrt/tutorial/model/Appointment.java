package com.staxrt.tutorial.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "\"Appointment\"")
@EntityListeners(AuditingEntityListener.class)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    //    @ManyToOne
    //    @JoinColumn(name = "uid")
    @Column(name = "uid", nullable = false)
    private int uid;

    //    @ManyToOne
    //    @JoinColumn(name = "did")
    @Column(name = "did", nullable = false)
    private int did;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "detail")
    private String detail;

    @Column(name = "date_create", nullable = false)
    private String date_create; // DateTime?

    @Column(name = "date_change", nullable = false)
    private String date_change; // DateTime?

    @Column(name = "date", nullable = false)
    private String date; // DateTime?

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "note")
    private String note;

    @Column(name = "user_note")
    private String user_note;

    @Column(name = "status")
    private String status;  // enum


    ///////////////////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getDate_change() {
        return date_change;
    }

    public void setDate_change(String date_change) {
        this.date_change = date_change;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUser_note() {
        return user_note;
    }

    public void setUser_note(String user_note) {
        this.user_note = user_note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}