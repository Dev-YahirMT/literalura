package com.codigoprueba.literalura.service;

import com.codigoprueba.literalura.Model.Autores;
import com.codigoprueba.literalura.Model.DatosLibro;
import com.codigoprueba.literalura.Model.Libros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibrosService {
    @Autowired
    private AutoresRepository autoresRepository;

    public Libros crearLibro(DatosLibro libroRecord){

        Optional<Autores> autoOpt = autoresRepository.findByNombre(libroRecord.Author().get(0).nombre());

        Autor autor;
        if (autorOpt.isPresent()) {
            autor = autorOpt.get();  // Autor ya existe
        } else {
            autor = new Autor(libroRecord.autor().id(), libroRecord.autor().nombre());
            autorRepository.save(autor);  // Guardar el nuevo autor
        }

    }
}
