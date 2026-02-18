package com.cristinavegas.holaMundo.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Shows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int year;

    //añadimos la propiedad para que sea bodireccional
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "shows")
    //usamos actor porque en la bbdd es actor
    private Set<Actor> actor = new HashSet<>();

    //esto me permite escribir new show()
    public Shows() {
    }

    public Shows(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Actor> getActor() {
        return actor;
    }

    public void setActor(Set<Actor> actor) {
        this.actor = actor;
    }

    //creamos un metodo que nos devuelva una lista de nombres
    public List<String> getActorNames() {
        return actor.stream() //con esto procesamos los datos del set de actores
                //con esos datos hare un map, de cada uno de los actores y tiene que hacer un .getName
                .map(eachActor -> eachActor.getName())
                //colecciona todos los datos del map y los conviertes en una lista
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Shows{" +
                "  \nid=" + id +
                "  \name='" + name + '\'' +
                ", \nyear=" + year +
                //con el metodo anterior rellenamos el toString
                ", \nactor=" + getActorNames() +
                '}';
    }

    //añadimos un ShowsRepository
}
