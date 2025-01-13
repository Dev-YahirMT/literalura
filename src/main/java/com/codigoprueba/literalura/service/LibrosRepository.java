package com.codigoprueba.literalura.service;

import com.codigoprueba.literalura.Model.Libros;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibrosRepository extends JpaRepository <Libros, Long> {
    @EntityGraph(attributePaths = "autores")
    List<Libros> findAll();

    Optional<Libros> findByTitulo(String nombre);

//    @Query("SELECT l FROM libros l ORDER BY l.descargas DESC LIMIT 10 ")
//    List<Libros> top10Descargas();
    List<Libros> findTop10ByOrderByDescargasDesc();
}