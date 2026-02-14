package com.cristinavegas.holaMundo.models;


import jakarta.persistence.*;

@Entity
//esto cambia el nombre de la tabla
//@Table(name="actores_de_series")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //esto lo que va a hacer es que me va a poner en la tabla los strings que he puesto en la clase role
    //al actualizar la tabla sale la nueva columna y detecta el enum de la clase role
    @Enumerated(EnumType.STRING)
    private Role role;

    //@Column(name = "first_name") //este va a ser el nombre de la columna
    private String name;

    private String residenceCountry;

    public Actor() {
    }

    public Actor(int id, String name, String residenceCountry) {
        this.id = id;
        this.name = name;
        this.residenceCountry = residenceCountry;
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

    public String getResidenceCountry() {
        return residenceCountry;
    }

    public void setResidenceCountry(String residenceCountry) {
        this.residenceCountry = residenceCountry;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", residenceCountry='" + residenceCountry + '\'' +
                '}';
    }
}
