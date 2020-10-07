package com.staxrt.tutorial.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "\"NotificationToken\"")
@EntityListeners(AuditingEntityListener.class)
public class NotificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", updatable = false, nullable = false)
    public int id;

//    @ManyToOne
//    @JoinColumn(name = "uid")
    @Column(name = "uid")
    private int uid;

    @Column(name = "fcm_token")
    private String fcm_token;

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

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }
}
