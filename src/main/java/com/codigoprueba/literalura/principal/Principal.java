package com.codigoprueba.literalura.principal;

import com.codigoprueba.literalura.Model.Libros;
import com.codigoprueba.literalura.Model.Result;
import com.codigoprueba.literalura.Model.DatosLibro;
import com.codigoprueba.literalura.service.ConsumoApi;
import com.codigoprueba.literalura.service.ConvierteDatos;
import com.codigoprueba.literalura.service.LibrosRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibrosRepository repositorio;

    // Crear el ObjectMapper
    ObjectMapper objectMapper = new ObjectMapper();

    public Principal(LibrosRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {

        var menu = """
                    1 - Buscar series 
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                                  
                    0 - Salir
                    """;
        System.out.println(menu);

        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+"));
//        var json = consumoApi.obtenerDatos("https://gutendex.com/books?ids=1");
        System.out.println(URL_BASE + nombreSerie.replace(" ", "+"));
        System.out.println("JSON");
        System.out.println(json);

        Result datos = conversor.obtenerDatos(json, Result.class);
        System.out.println("Datos");
        System.out.println(datos);

        Result persona = null;

        try {
            // Crear el ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Convertir el objeto a un String JSON
            String jsonString = objectMapper.writeValueAsString(datos);

            // Imprimir el resultado
            System.out.println(jsonString);

            System.out.println("------Objeto Record");

            // Deserializar el JSON en el record Persona
            persona = objectMapper.readValue(jsonString, Result.class);

            // Imprimir el resultado
            System.out.println(persona);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(persona.Libros().get(0));

        System.out.println("Guardar en base");

        Libros nLibros = new Libros(datos);
        repositorio.save(nLibros);



    }
}
