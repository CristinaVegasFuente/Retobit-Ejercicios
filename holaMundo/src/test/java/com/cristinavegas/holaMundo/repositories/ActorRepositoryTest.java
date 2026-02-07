package com.cristinavegas.holaMundo.repositories;


import com.cristinavegas.holaMundo.models.Actor;
import com.cristinavegas.holaMundo.models.Roles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ActorRepositoryTest {

    @Autowired
    private ActorRepository actorRepository;

    @Test
    @DisplayName("Podemos crear un actor")
    public void createActor() {
        Actor actor1 = new Actor();

        actor1.setName("Henrry Cavil");
        actor1.setResidentCountry("United States");

        Actor result = actorRepository.save(actor1);

        System.out.println("Esto es el resultado: " + result);

        assertNotNull(result);
        assertEquals("Henrry Cavil", result.getName());
        assertEquals("United States", result.getResidentCountry());
    }

    @Test
    @DisplayName("Podemos crear un actor")
    public void createActor2() {
        Actor actor2 = new Actor();

        actor2.setName("Chris Evans");
        actor2.setResidentCountry("United States");

        Actor result = actorRepository.save(actor2);

        System.out.println("Esto es el resultado: " + result);

        assertNotNull(result);
        assertEquals("Chris Evans", result.getName());
        assertEquals("United States", result.getResidentCountry());
    }

    @Test
    @DisplayName("Podemos leer datos")
    public void readActor() {
        Optional<Actor> optionalActor = actorRepository.findById(2);

        if (optionalActor.isPresent()) {
            Actor receivedActor = optionalActor.get();

            System.out.println("Recibimos el actor: " + receivedActor);

            assertEquals("Henrry Cavill", receivedActor.getName());
            assertEquals("United States", receivedActor.getResidentCountry());
        }
    }

    @Test
    @DisplayName("Podemos leer todos los datos de la base de datos")
    public void readAllActors() {
        List<Actor> actorList = actorRepository.findAll();

        System.out.println("Llena el resultado: " + actorList);

        assertFalse(actorList.isEmpty());
    }

    @Test
    @DisplayName("Actualizar entities de actor")
    public void updateActor() {
        Optional<Actor> optionalCavill = actorRepository.findById(2);

        if(optionalCavill.isPresent()) {
            Actor cavill = optionalCavill.get();
            cavill.setName("Chris Evans");
            actorRepository.save(cavill);

        }
    }

    @Test
    @DisplayName("Actualizar roles de actor")
    public void updateActorRole() {
        Optional<Actor> optionalCavill = actorRepository.findById(1);
        Optional<Actor> optionalEvans = actorRepository.findById(2);

        if(optionalCavill.isPresent() && optionalEvans.isPresent()) {
            Actor cavill = optionalCavill.get();
            Actor evans = optionalEvans.get();

            cavill.setRoles(Roles.GUEST);
            evans.setRoles(Roles.MAIN);

            List<Actor> savedActors = actorRepository.saveAll(List.of(cavill, evans));
            //System.out.println("Llena el resultado: " + savedActors);

            assertEquals(Roles.GUEST, savedActors.get(0).getRoles());
            assertEquals(Roles.MAIN, savedActors.get(1).getRoles());
        }
    }

    @Test
    @DisplayName("Podemos eliminar el actor")
    public void deleteActor() {
        actorRepository.deleteById(2);

        System.out.println("Eliminado el actor: ");

    }

    @Test
    @DisplayName("Podemos eliminar Todo")
    public void deleteAll() {
        actorRepository.deleteAll();
        //comprobamos que esta vacio
        List<Actor> actorList = actorRepository.findAll();

        assertTrue(actorList.isEmpty());
    }

    @Test
    @DisplayName("Buscar por nombre")
    public void getByName() {
        //usando findByNambe me devuelve solo lo que yo busque por ejemplo Alison Pill
        List<Actor> actor1 = actorRepository.findByName("Alison Pill");

        System.out.println(actor1);
    }

    @Test
    @DisplayName("Buscar por contiene en nombre")
    public void getByNameContaining() {
        //en cambio usando la palabra containing me devuelve todos los registros que lleven el nombre de Alison
        //pero antes tengo que cambiar el repositorio para que no me de error
        List<Actor> actor1 = actorRepository.findByNameContaining("Alison");

        System.out.println(actor1);
    }

    @Test
    @DisplayName("Buscar por pais de residencia")
    public void getByCountry() {
        System.out.println(actorRepository.countByResidentCountry("JAPAN"));
    }

    @Test
    @DisplayName("Borrar por nombre")
        public void deletedByName() {
        actorRepository.deleteByName("Jin Ha");
        }

    @Test
    @DisplayName("Borrar por nombre de pais")
    public void deletedByresidentCountry() {
        actorRepository.deleteByresidentCountry("Australia");
    }

    @Test
    @DisplayName("Podemos leer datos de un actor y su biografia")
    public void readActorBiografia() {
        //buscando por el id del actor, con lo cual la relacion se ha establecido y nos devuelve un objeto de tipo biografia
        Optional<Actor> optionalActor = actorRepository.findById(4);

        if (optionalActor.isPresent()) {
            Actor receivedActor = optionalActor.get();

            System.out.println("Recibimos el actor: " + receivedActor);

            assertEquals("Adam Scott", receivedActor.getName());
            assertEquals("USA", receivedActor.getResidentCountry());
        }

        assertTrue(optionalActor.isPresent());
    }

}
