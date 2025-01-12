package com.codigoprueba.literalura.Model;

import jakarta.persistence.*;
import com.codigoprueba.literalura.Model.Libros;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Autores")
public class Autores {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String birth_year;
    private String death_year;
    @OneToMany(mappedBy = "autores", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libros> libros = new ArrayList<>();

    public Autores(){}

    public Autores(Autor autor){
        this.nombre = autor.nombre();
        this.birth_year = autor.birth_year();
        this.death_year = autor.death_year();
    }
}

