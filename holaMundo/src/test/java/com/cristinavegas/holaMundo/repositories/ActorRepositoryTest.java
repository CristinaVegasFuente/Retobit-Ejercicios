package com.cristinavegas.holaMundo.repositories;

import com.cristinavegas.holaMundo.models.Actor;
import com.cristinavegas.holaMundo.models.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


//esta anotacion india a Spring que esta clase es un test
@SpringBootTest
public class ActorRepositoryTest {

    @Autowired//Esta anotacion es la que inyecta el repositorio
    //crea una instancia de ActorRepository y la guarda en actorRepository
    private ActorRepository actorRepository;

    @Test
    @DisplayName("Crear un actor")
    public void createActor() {
        Actor actor1 = new Actor();

        actor1.setName("Brit Lower");
        actor1.setResidenceCountry("United States");

        Actor result = actorRepository.save(actor1); //Esto es un INSERT
        System.out.println("Esto es el resultado: " + result);

        assertNotNull(result);
        assertEquals("Brit Lower", result.getName());
        assertEquals("United States", result.getResidenceCountry());
    }

    @Test
    @DisplayName("Leer datos de un actor")
    public void readActor() {
        //el cambio de variable a optiona es para que puede que haya un actor o puede que no haya nada
        Optional<Actor> optionalActor = actorRepository.findById(2);

        //Aqui lo que hacemos es que si hay un actor con el id = 2 lo comprueba
        if (optionalActor.isPresent()) {
            //crea el objeto actor y lo saca del tipo optionaActor
            Actor receivedActor = optionalActor.get();
            System.out.println("Recibimos: " + receivedActor);

            assertEquals("Brit Lower", receivedActor.getName());
            assertEquals("United States", receivedActor.getResidenceCountry());
        }
        assertTrue(optionalActor.isPresent());
    }

    @Test
    @DisplayName("Leer datos de un actor que no existe")
    public void readActorNoExist() {
        //el cambio de variable a optiona es para que puede que haya un actor o puede que no haya nada
        Optional<Actor> optionalActor = actorRepository.findById(3);

        //Aqui lo que hacemos es que si hay un actor con el id = 2 lo comprueba
        if (optionalActor.isPresent()) {
            //crea el objeto actor y lo saca del tipo optionaActor
            Actor receivedActor = optionalActor.get();
            System.out.println("Recibimos: " + receivedActor);

            assertEquals("Brit Lower", receivedActor.getName());
            assertEquals("United States", receivedActor.getResidenceCountry());
        }
        //este test no pasa porque en el assertion hemos puesto True y no pasa porque no existe
        assertTrue(optionalActor.isPresent());
    }

    @Test
    @DisplayName("Leer datos de todos los actores")
    public void readActors() {
        //Esto lo que hace es crear un array tipo lista
        List<Actor> actorList= actorRepository.findAll();
        //aqui lo que imprime por consola es algo asi: [Actor{id=1, role=null, name='Brit Lower', residenceCountry='United States'}]
        System.out.println(actorList);

        //esto es para asegurarse que no esta vacia
        assertTrue(!actorList.isEmpty());
        //otra forma mas sencilla es lo mismo pero asi:
        //assertFalse(actorList.isEmpty);
    }

    @Test
    @DisplayName("Crear otro actor")
    public void createOtherActor() {
        Actor actor2 = new Actor();

        actor2.setName("Adam Scott");
        actor2.setResidenceCountry("United States");

        Actor result = actorRepository.save(actor2); //Esto es un INSERT
        System.out.println("Esto es el resultado: " + result);

        assertNotNull(result);
        assertEquals("Adam Scott", result.getName());
        assertEquals("United States", result.getResidenceCountry());
    }

    @Test
    @DisplayName("Actualizar Entities de Actor")
    public void updateActor() {
        //Para hacer el Update necesitamos primero recibir la informacion del actor que vamos a modificar
        //En este caso sabemos que Brit Lower es la 2
        Optional<Actor> optionalBritt = actorRepository.findById(2);
        //Comprobamos que esta
        if (optionalBritt.isPresent()) {
            //sacamos el objeto y que lo convierta en una instamcia de esta clase
            Actor britt = optionalBritt.get();
            //una vez lo tenemos, cambiamos el nombre, ya que estaba con una sola "t" y lleva dos
            britt.setName("Britt Lower");
            //ademas vamos a ponerle un rol de tipo MAIN
            //importamos la clase ENUM de Role para quitar el fallo
            britt.setRole(Role.MAIN);
            //una vez cambiado el nombre, usamos el repository y le pasamos toda la entity Actor
            actorRepository.save(britt);
        }
    }

    @Test
    @DisplayName("Actualizar Entities de Actor")
    public void updateActors() {
        //Para hacer el Update necesitamos primero recibir la informacion del actor que vamos a modificar
        //En este caso sabemos que Brit Lower es la 2
        Optional<Actor> optionalBritt = actorRepository.findById(2);
        //En este caso sabemos que Adam Scott es el 3
        Optional<Actor> optionalAdam = actorRepository.findById(3);
        //Comprobamos que estan los dos y concatenamos con &&
        if (optionalBritt.isPresent() && optionalAdam.isPresent()) {
            Actor britt = optionalBritt.get();
            //Añado a Adam para modificar solo su Rol
            Actor adam = optionalAdam.get();
            britt.setName("Britt Lower");
            britt.setRole(Role.GUEST);
            adam.setRole(Role.MAIN);
            //una vez cambiado el nombre, usamos el repository usamos saveAll para guardar los dos
            //dentro del parentesis creamos una lista y añadimos los dos actores a modificar
            List<Actor> savedActors = actorRepository.saveAll(List.of(britt, adam));
            //aqui comprobamos que devuelve por consola
            //System.out.println("Lista de actores modificados: " + savedActors);
            //como podemos recibir el los cambios
            assertEquals(Role.GUEST, savedActors.get(0).getRole());
            assertEquals(Role.MAIN, savedActors.get(1).getRole());
        }
    }

    @Test
    @DisplayName("Eliminar un actor")
    public void deleteActor() {
        //Uso el repositorio para solo borrar a Adam Scott con el id=3
        actorRepository.deleteById(3);
    }

    @Test
    @DisplayName("Elimiar a todos los actores")
    public void deleteAllActors(){
        //uso deleteAll() para borrar a todos
        actorRepository.deleteAll();

        //hacemos un repository con findAll para buscar todos
        List<Actor> actorList = actorRepository.findAll();
        //una vez borrados los actores, comprobamos que esta vacia la lista
        assertTrue(actorList.isEmpty());
    }

    //metodos de busqueda derivados
    @Test
    @DisplayName("Busqueda de actores por nombre")
    public void findActorByName(){
        //busco por findByName, ya que he registrado ese metodo en el repositorio
        List<Actor> actorx = actorRepository.findByName("Cailee Spaeny");
        //que imprima por consola
        System.out.println("Resultado de buscar por nombre: " + actorx);
    }

    //metodo que buscar por nombre que contenga "x" es mas flexible y puede dar varias lineas encontradas
    @Test
    @DisplayName("Busqueda de actores por nombre que contiene x")
    public void findActorByNameContaining(){
        //busco por findByNameContaining, ya que he registrado ese metodo en el repositorio
        List<Actor> actorx = actorRepository.findByNameContaining("Cailee");
        //que imprima por consola
        System.out.println("Resultado de buscar por nombre: " + actorx);
    }

    //Eliminar por nombre
    @Test
    @DisplayName("Eliminar actor por nombre")
    public void deleteActorByName(){
        actorRepository.deleteByName("Cailee Spaeny");
    }

    //Eliminar por ResidenceCountry
    @Test
    @DisplayName("Eliminar actor por residenceCountry")
    public void deleteActorByResidenceCountry(){
        actorRepository.deleteByResidenceCountry("USA");
    }

    //Metodo para buscar y contar por pais usando JPQL en el repositorio
    @Test
    @DisplayName("Contar por país")
    public void countByResidenceCountry(){
        //Para verlo por consola metemos este metodo en un sout
        //actorRepository.countByResidenceCountry("United States");
        System.out.println(actorRepository.countByResidenceCountry("United States"));
    }

    //Metodo para buscar y contar por pais usando Query nativas en el repositorio
    @Test
    @DisplayName("Contar por país")
    public void countByResidenceCountryNative(){
        //Para verlo por consola metemos este metodo en un sout
        //actorRepository.countByResidenceCountry("United States");
        System.out.println(actorRepository.countByResidenceCountry("Japan"));
    }

    //metodo de busqueda compuesta
    @Test
    @DisplayName("Buscar por nombre y por pais")
    public void findByNameContainingAndResidenceCountry(){
        List<Actor> actorNameAndCountryContaining =
                actorRepository.findByNameContainingAndResidenceCountryContaining("man", "States");
        System.out.println("Resultado: " + actorNameAndCountryContaining);
    }

    /* Ejemplo de una relacion @OneToOne con Biography unidireccional, quiere decir que cuando consulto un actor
    me sale con la biografia pero cuando consulto solo la biografia no me sale el actor */
    @Test
    @DisplayName("Leer datos de un actor y su biografia")
    public void readActorBiography() {
        //el cambio de variable a optional es para que puede que haya un actor o puede que no haya nada
        Optional<Actor> optionalActor = actorRepository.findById(4);

        //Aqui lo que hacemos es que si hay un actor con el id = 3 lo comprueba
        if (optionalActor.isPresent()) {
            Actor receivedActor = optionalActor.get();
            /* aqui solo recibimos el actor, pero como queremos ver su biografia vamos a la Entity de Actor y
            creamos el Getter y el Setter de biografia */
            System.out.println("Recibimos: " + receivedActor.getBiography());
            //con esto leemos el objeto: Recibimos: Biography{id=1, bio='Adam Scott es un actor, comediante, director y productor...'}
            /* para que nos saliese toda la info del actor sin usar .getBiography() debemos completar el toString de
            la entidad de Actor con biography */
            System.out.println("Todo sobre el actor:" + receivedActor);

            assertEquals("Adam Scott", receivedActor.getName());
            assertEquals("United States", receivedActor.getResidenceCountry());
        }
        assertTrue(optionalActor.isPresent());
    }

    @Test
    @DisplayName("Leer datos de un actor y su agencia")
    public void readActorAgency() {
        //el cambio de variable a optional es para que puede que haya un actor o puede que no haya nada
        Optional<Actor> optionalActor = actorRepository.findById(4);

        //Aqui lo que hacemos es que si hay un actor con el id = 4 lo comprueba
        if (optionalActor.isPresent()) {
            Actor receivedActor = optionalActor.get();
            System.out.println("Todo sobre el actor:" + receivedActor);

            assertEquals("Adam Scott", receivedActor.getName());
            assertEquals("United States", receivedActor.getResidenceCountry());
        }
        assertTrue(optionalActor.isPresent());
    }
}
