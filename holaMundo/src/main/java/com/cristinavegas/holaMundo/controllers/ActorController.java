package com.cristinavegas.holaMundo.controllers;

import com.cristinavegas.holaMundo.dtos.ActorUpDateNameDTO;
import com.cristinavegas.holaMundo.models.Actor;
import com.cristinavegas.holaMundo.service.ActorServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST encargado de gestionar las operaciones CRUD
 * relacionadas con la entidad Actor.
 * Se comunica con la capa de negocio a través de ActorServiceImpl
 * y expone endpoints HTTP bajo la ruta base:
 *      /api/actors
 * Este controlador permite:
 *  - Consultar actores (todos o filtrados por nombre)
 *  - Consultar un actor por ID
 *  - Crear un nuevo actor
 *  - Actualizar completamente un actor
 *  - Actualizar parcialmente un actor
 *  - Eliminar un actor
 */
@RestController
// Define la ruta base común para todos los endpoints del controlador
@RequestMapping("/api/actors")
public class ActorController {

    /**
     * Inyección de dependencias del servicio.
     * Spring se encarga de proporcionar automáticamente una instancia
     * de ActorServiceImpl en tiempo de ejecución.
     */
    @Autowired
    // Inyectamos el servicio de implementación
            ActorServiceImpl actorService;

    /**
     * Endpoint de verificación para comprobar que la API está activa.
     * Ejemplo:
     * GET http://localhost:8080/api/actors/test
     */
    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck() {
        return "API funcionando correctamente";
    }

    /**
     * Endpoint que devuelve la lista de actores.
     * Permite filtrar opcionalmente por nombre mediante un parámetro en la URL.
     * Ejemplo:
     * GET http://localhost:8080/api/actors
     * GET http://localhost:8080/api/actors?name=lower
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    // @RequestParam permite recibir parámetros de la URL
    // required = false indica que el parámetro es opcional
    public List<Actor> getAllActors(@RequestParam(required = false) String name) {
        return actorService.getAllActors(name);
    }

    /**
     * Endpoint que obtiene un actor específico a partir de su ID.
     * El ID se recibe como parte de la URL (variable de ruta).
     * Ejemplo:
     * GET http://localhost:8080/api/actors/9
     */
    @GetMapping("/{actorId}")
    @ResponseStatus(HttpStatus.OK)
    public Actor getActorById(@PathVariable Integer actorId) {
        return actorService.getActorById(actorId);
    }

    /**
     * Endpoint que crea un nuevo actor en la base de datos.
     * Se ejecuta cuando el cliente envía una petición POST a:
     * POST http://localhost:8080/api/actors
     */
    @PostMapping
    // Devuelve 201 CREATED cuando el recurso se crea correctamente
    @ResponseStatus(HttpStatus.CREATED)
    // @RequestBody indica que los datos del actor se reciben en formato JSON
    // dentro del cuerpo de la petición y Spring los convierte automáticamente en un objeto Actor
    // @Valid comprueba todas las validaciones definidas en la entidad Actor
    public Actor createActor(@Valid @RequestBody Actor actor) {
        return actorService.createActor(actor);
    }

    /**
     * Endpoint que actualiza completamente un actor existente mediante su ID.
     * Se utiliza PUT cuando el cliente envía todos los datos del objeto a reemplazar.
     * Ejemplo:
     * PUT http://localhost:8080/api/actors/18
     */
    @PutMapping("/{actorId}")
    @ResponseStatus(HttpStatus.OK)
    // @RequestBody recibe el objeto Actor en formato JSON desde el cuerpo de la petición
    // @PathVariable obtiene el ID del actor desde la URL
    // @Valid comprueba las validaciones definidas en la entidad Actor
    public Actor updateActor(@Valid @RequestBody Actor actor,
                             @PathVariable Integer actorId) {
        return actorService.updateActor(actorId, actor);
    }

    /**
     * Endpoint que actualiza parcialmente un actor.
     * Se utiliza PATCH cuando solo se desea modificar un campo específico
     * sin reemplazar completamente el objeto.
     * En este caso, únicamente se actualiza el atributo "name".
     * Ejemplo:
     * PATCH http://localhost:8080/api/actors/18/name
     */
    @PatchMapping("/{actorId}/name")
    @ResponseStatus(HttpStatus.OK)
    // @RequestBody recibe el nuevo valor del campo a modificar en formato JSON
    // @PathVariable obtiene el ID del actor desde la URL
    // Se utiliza un DTO (ActorUpDateNameDTO) en lugar de la entidad completa
    public Actor patchActor(@Valid @RequestBody ActorUpDateNameDTO actor,
                            @PathVariable Integer actorId) {
        return actorService.patchActor(actorId, actor);
    }

    /**
     * Endpoint que elimina un actor de la base de datos a partir de su ID.
     * Se utiliza DELETE cuando se desea eliminar un recurso.
     *
     * Ejemplo:
     * DELETE http://localhost:8080/api/actors/18
     */
    @DeleteMapping("/{actorId}")
    // Devuelve 204 NO CONTENT para indicar que la operación fue exitosa
    // pero no hay contenido en la respuesta
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // @PathVariable obtiene el ID del actor desde la URL
    public void deleteActor(@PathVariable Integer actorId) {
        actorService.deleteActor(actorId);
    }
}