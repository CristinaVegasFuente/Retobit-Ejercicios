package com.cristinavegas.holaMundo.controllers;

import com.cristinavegas.holaMundo.models.Actor;
import com.cristinavegas.holaMundo.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST encargado de gestionar las operaciones CRUD
 * relacionadas con la entidad Actor.
 * Se comunica con la capa de persistencia a través de ActorRepository
 * y expone endpoints HTTP bajo la ruta base:
 *      /api/actors
 * Este controlador permite:
 *  - Consultar actores (todos o filtrados por nombre)
 *  - Consultar un actor por ID
 *  - Crear un nuevo actor
 *  - Actualizar completamente un actor
 *  - Actualizar parcialmente un actor
 */
@RestController
//Define la ruta base común para todos los endpoints del controlador
@RequestMapping("/api/actors")
public class ActorController {

    /**
     * Endpoint de verificación para comprobar que la API está activa.
     * Ejemplo:
     * GET http://localhost:8080/api/actors/test
     */
    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck(){
        return "API funcionando correctamente";
    }

    /**
     * Inyección de dependencias del repositorio
     * Spring se encarga de proporcionar automáticamente una instancia de ActorRepository en tiempo de ejecución.
     * Esto permite acceder a la base de datos sin crear manualmente el objeto
     */
    @Autowired
    ActorRepository actorRepository;

    /**
     * Endpoint que devuelve la lista de actores.
     * Permite filtrar opcionalmente por nombre mediante un parámetro en la URL.
     * Ejemplo desde Postman:
     * GET http://localhost:8080/api/actors
     * GET http://localhost:8080/api/actors?name=lower
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    // @RequestParam permite recibir parámetros de la URL
    // required = false indica que el parámetro es opcional
    public List<Actor> getAllActors(@RequestParam(required = false) String name){
        //Si el cliente envía el parámetro "name" se realiza una búsqueda por coincidencia parcial del nombre
        if (name != null) {
            //usamos del repositorio el metodo findByNameContaining y le pasamos el nombre
            return actorRepository.findByNameContaining(name);
        }
        //Si no se envía ningún parámetro, se devuelven todos los actores almacenados
        return actorRepository.findAll();
    }

    /**
     * Endpoint que obtiene un actor específico a partir de su ID
     * El ID se recibe como parte de la URL (variable de ruta)
     * Ejemplo:
     * GET http://localhost:8080/api/actors/9
     */
    @GetMapping("/{actorId}")
    @ResponseStatus(HttpStatus.OK)
    public Actor getActorById(@PathVariable Integer actorId){
        //findById devuelve un Optional porque el registro puede no existir
        Optional<Actor> optionalActor = actorRepository.findById(actorId);
        //se comprueba si el actor está presente en la base de datos
        if(optionalActor.isPresent()){
            //Si existe, se obtiene el objeto contenido en el Optional
            return optionalActor.get();
        }else{
            //Si no existe, se lanza una excepción con estado 404 NOT FOUND
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor no encontrado");
        }
    }

    /**
     * Endpoint que crea un nuevo actor en la base de datos
     * Se ejecuta cuando el cliente envía una petición POST a:
     * POST http://localhost:8080/api/actors
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Devuelve 201 CREATED cuando el recurso se crea correctamente
    public Actor createActor(@RequestBody Actor actor){
        // @RequestBody indica que los datos del actor se reciben en formato JSON
        // dentro del cuerpo de la petición y Spring los convierte automáticamente en un objeto Actor

        // save() persiste el objeto en la base de datos
        // Si el ID es null, se inserta un nuevo registro
        return actorRepository.save(actor);
    }

    /**
     * Endpoint que actualiza completamente un actor existente mediante su ID.
     * Se utiliza PUT cuando el cliente envía todos los datos del objeto a reemplazar.
     * Ejemplo:
     * PUT http://localhost:8080/api/actors/18
     */
    @PutMapping("/{actorId}")
    @ResponseStatus(HttpStatus.OK)
    // @RequestBody recibe el objeto Actor en formato JSON desde el cuerpo de la petición.
    // @PathVariable obtiene el ID del actor desde la URL
    public Actor updateActor(@RequestBody Actor actor, @PathVariable Integer actorId){

        // Se busca el actor en la base de datos
        // Si no existe, se lanza automáticamente una excepción 404 NOT FOUND
        Actor foundActor = actorRepository.findById(actorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor no encontrado"));

        // Se actualizan explícitamente los campos del objeto encontrado con los nuevos valores recibidos
        foundActor.setRole(actor.getRole());
        foundActor.setName(actor.getName());
        foundActor.setResidenceCountry(actor.getResidenceCountry());

        // Se guardan los cambios en la base de datos
        return actorRepository.save(foundActor);
    }

    /**
     * Endpoint que actualiza parcialmente un actor.
     * Se utiliza PATCH cuando solo se desea modificar un campo específico sin reemplazar completamente el objeto.
     * En este caso, únicamente se actualiza el atributo "name"
     * Ejemplo:
     * PATCH http://localhost:8080/api/actors/18/name
     */
    @PatchMapping("/{actorId}/name")
    @ResponseStatus(HttpStatus.OK)
    // @RequestBody recibe el nuevo valor del campo a modificar en formato JSON.
    // @PathVariable obtiene el ID del actor desde la URL
    public Actor patchActor(@RequestBody Actor actor, @PathVariable Integer actorId){
        // Se busca el actor en la base de datos.
        // Si no existe, se lanza una excepción con estado 404 NOT FOUND.
        Actor foundActor = actorRepository.findById(actorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor no encontrado"));

        // Solo se actualiza el campo indicado en la ruta (name),
        // manteniendo intactos los demás atributos del objeto.
        foundActor.setName(actor.getName());

        // Se guardan los cambios en la base de datos.
        return actorRepository.save(foundActor);
    }
}
