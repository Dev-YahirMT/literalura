package com.codigoprueba.literalura.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

@Entity
@Table(name = "Libros")
public class Libros {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Integer idApi;
    private String titulo;
    private Integer descargas;
    private String idioma;
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autores autores;

    @Transient
    ObjectMapper objectMapper = new ObjectMapper();

    public Libros(){}

    public Libros(Result Resultado){
        this.titulo = Resultado.Libros().get(0).title();
        this.idApi = Resultado.Libros().get(0).id();
        this.descargas = Resultado.Libros().get(0).download_count();
        try {
            this.idioma = objectMapper.writeValueAsString(Resultado.Libros().get(0).languages());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.autores = new Autores(Resultado.Libros().get(0).Author().get(0));
    }
    public Libros(DatosLibro Resultado, Autores autor){
        this.titulo = Resultado.title();
        this.idApi = Resultado.id();
        this.descargas = Resultado.download_count();
        try {
            this.idioma = objectMapper.writeValueAsString(Resultado.languages());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.autores = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdApi() {
        return idApi;
    }

    public void setIdApi(Integer idApi) {
        this.idApi = idApi;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Autores getAutores() {
        return autores;
    }

    public void setAutores(Autores autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "Libros{" +
                "id=" + id +
                ", idApi=" + idApi +
                ", titulo='" + titulo + '\'' +
                ", descargas=" + descargas +
                ", idioma='" + idioma + '\'' +
                ", autores=" + autores +
                ", objectMapper=" + objectMapper +
                '}';
    }
}
