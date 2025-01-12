package com.codigoprueba.literalura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro (
        @JsonAlias("count") Integer id,
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<Autor> Author,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Integer download_count
){

}
