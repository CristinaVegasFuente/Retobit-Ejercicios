package dev.marshall_bits.repositories.repositories;

import dev.marshall_bits.repositories.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

//para el repositorio, extendemos la interfaz a JpaRepository.
//en la entidad User el atributo ID es Long, si fuese int, ponemos Integer
public interface UserRepository extends JpaRepository<User, Long> {

    //Nos pide un usuario por nombre
    User findByUsername (String username);

    //nos pide un usuario por email
    User findByEmail (String email);

}
