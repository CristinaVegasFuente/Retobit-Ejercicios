package dev.marshall_bits.repositories.repositories;

import dev.marshall_bits.repositories.models.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.marshall_bits.repositories.models.Post;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//para el repositorio, extendemos la interfaz a JpaRepository.
public interface PostRepository extends JpaRepository<Post, Long> {

    //nos pide un POST concreto por titulo, usamos findByTitle
    Post findByTitle (String title);

    //Nos pide una LISTA, y como es un ENUM metemos en el parentesis la clase ENUM y el atributo category
    List<Post> findByCategory (PostCategory categoy);

    //de mas de 100, si quisieramos de menos es cambiar el operador aritmetico por este "<"
    @Query("SELECT p FROM Post p WHERE p.viewCount > 100")
    List<Post> findPostsWithMoreThan100Views();

    //de forma descendente, si quisieramos de forma ascendente es cambiar DESC por ASC
    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    List<Post> findAllByCreatedAt();

    //en Jpa es CONTAINING y en una Query es LIKE, usamos los porcentajes y seguimos usando los dos puntos para que sea variable
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword%")
    List<Post> findByTitleContaining(String keyword);
}
