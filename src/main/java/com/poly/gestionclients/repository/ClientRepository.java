package com.poly.gestionclients.repository;

import com.poly.gestionclients.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}