package com.cristinavegas.holaMundo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
//crea todos los getters
@Getter
//todos los setters
@Setter
//constructor sin argumentos
@NoArgsConstructor
//constructor con argumentos
@AllArgsConstructor
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
