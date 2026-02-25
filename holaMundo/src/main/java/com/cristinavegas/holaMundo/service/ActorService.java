package com.cristinavegas.holaMundo.service;

import com.cristinavegas.holaMundo.dtos.ActorUpDateNameDTO;
import com.cristinavegas.holaMundo.models.Actor;

import java.util.List;

/**
 * Interfaz que define las operaciones del servicio
 * relacionadas con la entidad Actor.
 * Establece el contrato que debe cumplir cualquier
 * clase que implemente la lógica de negocio para
 * la gestión de actores.
 * Incluye operaciones CRUD completas.
 */
public interface ActorService {
    List<Actor> getAllActors(String name);

    Actor getActorById(Integer actorId);

    Actor createActor(Actor actor);

    Actor updateActor(Integer actorId, Actor actor);

    Actor patchActor(Integer actorId, ActorUpDateNameDTO actorUpDateNameDTO);

    void deleteActor(Integer actorId);

    }
