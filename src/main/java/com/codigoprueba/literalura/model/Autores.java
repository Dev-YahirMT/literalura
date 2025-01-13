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
    public Autores(String cNombre, String CBirth_year, String CDeath_year){
        this.nombre = cNombre;
        this.birth_year = CBirth_year;
        this.death_year = CDeath_year;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getDeath_year() {
        return death_year;
    }

    public void setDeath_year(String death_year) {
        this.death_year = death_year;
    }
}

