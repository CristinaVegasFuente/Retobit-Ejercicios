package com.cristinavegas.holaMundo.repositories;

import com.cristinavegas.holaMundo.models.Agency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class AgencyRepositoryTest {

    @Autowired
    AgencyRepository agencyRepository;

    /* relacion bidireccional porque al pedir un actor no sale la agencia en la que esta inscrito, pero al buscar una
    agencia, nos saca todos los actores y actrices que estan en esa agencia inscritos */
    @Test
    @DisplayName("Recibimos agencia por ID")
    public void getAgency(){
        //metemos optional para que compruebe si existe la agencia con id = 1
        Optional<Agency> optionalAgency = agencyRepository.findById(1);

        if (optionalAgency.isPresent())
            System.out.println(optionalAgency.get());

    }
}
