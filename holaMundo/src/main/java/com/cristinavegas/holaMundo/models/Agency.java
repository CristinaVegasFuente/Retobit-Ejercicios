package com.cristinavegas.holaMundo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;

@Entity
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String city;

    //con mappedBy lo que hacemos es que diga oye, en la otra entidad busca la propiedad llamada agency
    //fetch es para que intente hacer todos los procesos y no salga el error de lazy
    @OneToMany(mappedBy = "agency", fetch = FetchType.EAGER)
    //esta anotacion: si hace referencia a algo que ya existe y la agencia hace referencia no lo incluye
    @JsonBackReference
    private List<Actor> actorList = new ArrayList<>();
    //una vez hecho esto creamos la propiedad Agency agency; en la Entidad Actor


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
