package com.codigoprueba.literalura.service;

import com.codigoprueba.literalura.Model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutoresRepository extends JpaRepository<Autores, Long> {
    Optional<Autores> findByNombre(String nombre);
}
