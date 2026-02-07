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

    @Test
    @DisplayName("Recibimos agencia por id")
    public void getAgency() {
        Optional<Agency> optionalAgency = agencyRepository.findById(1);

        if(optionalAgency.isPresent()){
            System.out.println(optionalAgency.get());
        }
    }

}
