package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by zach on 10/28/16.
 */
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String filename;

    @ManyToOne
    User user;

    public Image() {
    }

    public Image(String filename, User user) {
        this.filename = filename;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
