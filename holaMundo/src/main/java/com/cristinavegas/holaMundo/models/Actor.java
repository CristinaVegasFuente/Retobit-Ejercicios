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

    //Creamos la relacion con la biografia de cada actor
    @OneToOne
    Biography biography;

    //añadimos la propiedad con su relacion
    @ManyToOne
    Agency agency;

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

    //creamos el getter y setter para recibirlo por consola al ejecutar el metodo test de ReadActorBiography
    public Biography getBiography() {
        return biography;
    }

    public void setBiography(Biography biography) {
        this.biography = biography;
    }

    //añadimos Getters y Setters de Agency
    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }


    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", residenceCountry='" + residenceCountry + '\'' +
                ", biography=" + biography +
                //añadimos el toString de Agency
                /* para que no nos de un error de StackOverflowError, que basicamente es que este toString pide la
                agencias y al mismo tiempo el toString de Agency pide el ListActor, lo solucionamos con .getName
                para que nos de el nombre de la agencia del actor que estamos buscando*/
                ", agency=" + agency.getName() +
                '}';
    }
}
