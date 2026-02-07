package com.cristinavegas.holaMundo.repositories;

import com.cristinavegas.holaMundo.models.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepository extends JpaRepository<Agency, Integer> {
}
