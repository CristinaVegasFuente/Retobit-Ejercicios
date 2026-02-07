package com.cristinavegas.holaMundo.repositories;

import com.cristinavegas.holaMundo.models.Actor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

    //lista por nombre de forma estricta
    List<Actor> findByName(String name);
    //lista por nombre que contiene algo y puede dar varios registros
    List<Actor> findByNameContaining(String name);

    //esto nos indica aparte de recibir datos tambien los modifica
    @Transactional
    //el int es porque el delete nos devuelve el numero de elementos que ha borrado
    int deleteByName(String name);

    @Transactional
    int deleteByresidentCountry(String residentCountry);

    @Query("SELECT COUNT(a) FROM Actor a WHERE a.residentCountry = :country")
    int countByResidentCountry(String country);

}
