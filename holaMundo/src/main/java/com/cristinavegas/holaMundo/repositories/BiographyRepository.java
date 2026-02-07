package com.cristinavegas.holaMundo.repositories;

import com.cristinavegas.holaMundo.models.Biography;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiographyRepository extends JpaRepository<Biography, Integer> {
}
