package com.cristinavegas.holaMundo.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String city;

    @OneToMany(mappedBy = "agency", fetch = FetchType.EAGER)
    private List<Actor> actorList = new ArrayList<>();

    public Agency() {
    }

    public Agency(int id, String name, String city, List<Actor> actorList) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.actorList = actorList;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", actorList=" + actorList +
                '}';
    }
}
