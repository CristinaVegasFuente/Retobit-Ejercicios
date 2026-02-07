package com.cristinavegas.holaMundo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Biography {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String bio;

    public Biography() {
    }

    public Biography(int id, String bio) {
        this.id = id;
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Biography{" +
                "id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
