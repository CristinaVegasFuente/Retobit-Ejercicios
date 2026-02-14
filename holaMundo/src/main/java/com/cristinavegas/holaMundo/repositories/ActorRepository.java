package com.cristinavegas.holaMundo.repositories;

import com.cristinavegas.holaMundo.models.Actor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//interfaz con extension a jpaRepository, añadimos la entidad Actor y el tipo de dato identificador
//en este caso el identificador es un ID de tipo int que es Integer
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    //metodos derivados abstractos
    List<Actor>findByName(String name);

    //metodo que buscar por nombre que contenga "x"
    List<Actor>findByNameContaining(String name);

    //PARA LOS METODOS DERIVADOS QUE HAGAN MODIFICACIONES EN LA BBDD TENEMOS QUE AÑADIR LA ANOTACION DE TRANSACTIONAL
    @Transactional
    //metodo para eliminar, pongo int porque al eliminar me devuelve la cantidad de elementos eliminados
    int deleteByName(String name);

    @Transactional
    //Metodo para borrar por residenceCountry
    int deleteByResidenceCountry(String residenceCountry);

    //JPQL metemos la query y al meter :country es algo que puede variar
    @Query("SELECT COUNT(a) FROM Actor a WHERE a.residenceCountry = :country")
    //El metodo sabemos que va devolver un numero con lo cual ponemos int
    //Dentro del parentesis ponemos country igual que en la Query
    int countByResidenceCountry(String country);

    //SQL NATIVO
    @Query(value = "SELECT COUNT(*) FROM actor WHERE a.residence_country = :country", nativeQuery = true)
    int countByResidenceCountryNative(String country);

    //Busquedas compuestas
    List<Actor>findByNameContainingAndResidenceCountryContaining(String name, String ResidenceCountry);

}
