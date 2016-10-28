package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by zach on 10/28/16.
 */
@Entity
@Table(name = "recipients")
public class Recipient {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    Image image;

    @ManyToOne
    User user;

    public Recipient() {
    }

    public Recipient(Image image, User user) {
        this.image = image;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
