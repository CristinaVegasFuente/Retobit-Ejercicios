package com.cristinavegas.holaMundo.service;

import com.cristinavegas.holaMundo.dtos.ActorUpDateNameDTO;
import com.cristinavegas.holaMundo.models.Actor;
import com.cristinavegas.holaMundo.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio ActorService.
 * Contiene la lógica de negocio relacionada con la entidad Actor.
 * Actúa como intermediario entre el controlador y el repositorio,
 * gestionando las operaciones CRUD y el control de errores.
 * Se comunica con la base de datos a través de ActorRepository.
 */
@Service
public class ActorServiceImpl implements ActorService {

    /**
     * Repositorio encargado del acceso a datos.
     * Spring inyecta automáticamente la dependencia.
     */
    @Autowired
    ActorRepository actorRepository;

    /**
     * Obtiene todos los actores o filtra por nombre si se proporciona.
     */
    @Override
    public List<Actor> getAllActors(String name) {

        // Si el cliente envía el parámetro "name"
        // se realiza una búsqueda por coincidencia parcial del nombre
        if (name != null) {

            // Usamos el método findByNameContaining del repositorio
            return actorRepository.findByNameContaining(name);
        }

        // Si no se envía ningún parámetro,
        // se devuelven todos los actores almacenados
        return actorRepository.findAll();
    }

    /**
     * Obtiene un actor a partir de su identificador.
     */
    @Override
    public Actor getActorById(Integer actorId) {

        // findById devuelve un Optional porque el registro puede no existir
        Optional<Actor> optionalActor = actorRepository.findById(actorId);

        // Se comprueba si el actor está presente en la base de datos
        if (optionalActor.isPresent()) {

            // Si existe, se obtiene el objeto contenido en el Optional
            return optionalActor.get();

        } else {

            // Si no existe, se lanza una excepción con estado 404 NOT FOUND
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor no encontrado");
        }
    }

    /**
     * Crea un nuevo actor en la base de datos.
     */
    @Override
    public Actor createActor(Actor actor) {

        // save() persiste el objeto en la base de datos
        // Si el ID es null, se inserta un nuevo registro
        return actorRepository.save(actor);
    }

    /**
     * Actualiza completamente un actor existente.
     */
    @Override
    public Actor updateActor(Integer actorId, Actor actor) {

        // Se busca el actor en la base de datos
        // Si no existe, se lanza automáticamente una excepción 404 NOT FOUND
        Actor foundActor = actorRepository.findById(actorId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor no encontrado"));

        // Se actualizan explícitamente los campos del objeto encontrado
        foundActor.setRole(actor.getRole());
        foundActor.setName(actor.getName());
        foundActor.setResidenceCountry(actor.getResidenceCountry());

        // Se guardan los cambios en la base de datos
        return actorRepository.save(foundActor);
    }

    /**
     * Actualiza parcialmente un actor.
     * En este caso únicamente se modifica el atributo "name".
     */
    @Override
    public Actor patchActor(Integer actorId, ActorUpDateNameDTO actor) {

        // Se busca el actor en la base de datos
        // Si no existe, se lanza una excepción con estado 404 NOT FOUND
        Actor foundActor = actorRepository.findById(actorId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor no encontrado"));

        // Solo se actualiza el campo indicado (name)
        foundActor.setName(actor.getName());

        // Se guardan los cambios en la base de datos
        return actorRepository.save(foundActor);
    }

    /**
     * Elimina un actor de la base de datos.
     */
    @Override
    public void deleteActor(Integer actorId) {

        // Si el ID no existe, se lanza una excepción 404 NOT FOUND
        // Spring Data JPA no lanza excepción automáticamente al usar deleteById
        if (!actorRepository.existsById(actorId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor no encontrado");
        }

        // Se elimina el registro correspondiente en la base de datos
        actorRepository.deleteById(actorId);
    }
}