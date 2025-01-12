package com.codigoprueba.literalura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Autor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") String birth_year,
        @JsonAlias("death_year") String death_year
) {
}
