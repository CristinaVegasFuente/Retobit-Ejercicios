package com.cristinavegas.holaMundo.models;

import jakarta.persistence.*;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    //añado el rol principal, Main, Guest, Cameo, Supporting
    @Enumerated(EnumType.STRING)
    private Roles roles;

    private String name;

    private String residentCountry;

    @OneToOne
    Biography biography;

    @ManyToOne
    Agency agency;

    public Actor() {
    }

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

    public String getResidentCountry() {
        return residentCountry;
    }

    public void setResidentCountry(String residentCountry) {
        this.residentCountry = residentCountry;
    }

    public Roles getRoles() {
        return roles;
    }
    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Biography getBiography() {
        return biography;
    }

    public void setBiography(Biography biography) {
        this.biography = biography;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "roles=" + roles +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", residentCountry='" + residentCountry + '\'' +
                ", biography=" + biography +
                ", agency=" + agency.getName() +
                '}';
    }
}
