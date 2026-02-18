package com.cristinavegas.holaMundo.repositories;


import com.cristinavegas.holaMundo.models.Shows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ShowsRepositoryTest {

    @Autowired
    ShowsRepository showsRepository;

    @Test
    @DisplayName("Podemos Ver un Show con sus actores")
    public void getShowbyId(){
        Optional<Shows> optionalShows = showsRepository.findById(1);

        if (optionalShows.isPresent()) {
            System.out.println(optionalShows.get());
        }
    }
}
