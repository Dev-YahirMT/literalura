package com.codigoprueba.literalura.service;

import com.codigoprueba.literalura.Model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LibrosRepository extends JpaRepository <Libros, Long> {
}
