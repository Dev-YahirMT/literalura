package com.codigoprueba.literalura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.codigoprueba.literalura.Model.DatosLibro;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Result(
        @JsonAlias("count") String id,
        @JsonAlias("results") List<DatosLibro> Libros
) {
}
