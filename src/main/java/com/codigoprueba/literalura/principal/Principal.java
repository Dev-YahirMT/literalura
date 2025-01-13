package com.codigoprueba.literalura.principal;

import com.codigoprueba.literalura.Model.*;
import com.codigoprueba.literalura.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.function.Function;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibrosRepository repositorio;
    private AutoresRepository repositorioAutor;
    private List<Libros> librosP;
    private List<Autores> autoresP;

    // Crear el ObjectMapper
    ObjectMapper objectMapper = new ObjectMapper();

    public Principal(LibrosRepository repository, AutoresRepository repositoryAutor) {
        this.repositorio = repository;
        this.repositorioAutor = repositoryAutor;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion!=0) {
            System.out.println("------+------+------+------+------+------+------+------+");
            System.out.println("------+------+ Menu +------+------+");
            var menu = """
                    1 - Buscar libro WEB
                    2 - Ver libros buscados
                    3 - Ver lista de Autores
                    4 - Ver aurores vivos en un año
                    5 - Ver Cantidad de libros por idioma
                    6 - Top 10 de Descargas
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
               case 2:
                    verLibrosBuscados();
                    break;
                case 3:
                    verAutores();
                    break;
                case 4:
                    autoresVivos();
                    break;
                case 5:
                    librosCantidad();
                    break;
                case 6:
                    buscarTop10Libros();
                    break;
//                case 7:
//                    filtrarSeriesPorTemporadaYEvaluacion();
//                    break;
//                case 8:
//                    buscarEpisodiosPorTitulo();
//                    break;
//                case 9:
//                    buscarTop5Episodios();
//                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarTop10Libros() {

        List<Libros> top10Libros = repositorio.findTop10ByOrderByDescargasDesc();

        System.out.println("------+------+------+------+------+------+------+------+");
        System.out.println("------+------+ TOP 10 Libros +------+------+");
        int[] contador = {1};
        top10Libros.forEach(l-> System.out.printf("%d.- %s -- Descargas: %d\n",contador[0]++,l.getTitulo(), l.getDescargas()));

        System.out.println("------+------+------+------+------+------+------+------+");
        System.out.println("------+------+------+------+------+------+------+------+");

    }

    private void librosCantidad() {

        librosP = repositorio.findAll();

        System.out.println("------+------+------+------+------+------+------+------+");
        System.out.println("------+------+ Idiomas y cantidad de libros +------+------+");

        // Contar la cantidad de libros por idioma
        Map<String, Long> idiomaCount = librosP.stream()
                .flatMap(libro -> {
                    // Eliminar los corchetes y comillas
                    String cleanedIdiomas = libro.getIdioma()
                            .replace("[", "")      // Quitar corchetes de apertura
                            .replace("]", "")      // Quitar corchetes de cierre
                            .replace("\"", "");    // Quitar las comillas

                    // Dividir la cadena en idiomas separados por coma
                    String[] idiomas = cleanedIdiomas.split(",");

                    return Arrays.stream(idiomas);  // Convertir el array en un stream
                })
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // Agrupar y contar

        idiomaCount.forEach((idioma, count) -> System.out.println(idioma + ": " + count));

        System.out.println("------+------+------+------+------+------+------+------+");
        System.out.println("------+------+------+------+------+------+------+------+");


    }

    private void autoresVivos() {

        boolean esValido = false; // Bandera para controlar el bucle
        int fecha = 0;

        while (!esValido) {
            try {
                System.out.print("Ingrese el año a buscar con numero");
                fecha = teclado.nextInt();
                esValido = true;
            } catch (Exception e) {
                System.out.println("Error: Entrada no válida. Debes ingresar un número.");
                teclado.nextLine();
            }
        }

        System.out.println("------+------+------+------+------+------+------+------+");
        System.out.println("------+------+ Autores Vivos en "+fecha+" +------+------+");
        autoresP = repositorioAutor.findAll();

        int finalFecha = fecha;
        List<Autores> autoresFiltrados = autoresP.stream()
                .filter(autor ->
                        autor.getDeath_year() != null && // Excluir vivos
                                Integer.parseInt(autor.getDeath_year()) > finalFecha &&
                                Integer.parseInt(autor.getBirth_year()) < finalFecha)
                .toList();

        int[] contador = {1}; // Para numerar los resultados
        autoresFiltrados.stream()
                .map(autor -> String.format("%d.- %s Nacimiento: %s, Muerte: %s",
                        contador[0]++, autor.getNombre(), autor.getBirth_year(), autor.getDeath_year()))
                .forEach(System.out::println);

        System.out.println("------+------+------+------+------+------+------+------+");
        System.out.println("------+------+------+------+------+------+------+------+");

    }

    private void verAutores() {
        System.out.println("------+------+------+------+------+------+------+------+");
        System.out.println("------+------+ Autores +------+------+");
        autoresP = repositorioAutor.findAll();

        AtomicInteger contador = new AtomicInteger(1);

        autoresP.stream()
                .map(l -> String.format("%d .- %s",contador.getAndIncrement(), l.getNombre()))
                .forEach(System.out::println);

        System.out.println("------+------+------+------+------+------+------+------+");
        System.out.println("------+------+------+------+------+------+------+------+");
    }

    private void buscarLibroWeb() {
        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+"));
//        var json = consumoApi.obtenerDatos("https://gutendex.com/books?ids=1");
        System.out.println(URL_BASE + nombreSerie.replace(" ", "+"));
        System.out.println("JSON");
        System.out.println(json);

        Result datos = conversor.obtenerDatos(json, Result.class);

        System.out.println("Guardar en base");

        Autores autorRegistrado = guardarAutor(datos.Libros().get(0).Author().get(0));

        guardarLibro(datos.Libros().get(0), autorRegistrado);
    }

    private void verLibrosBuscados(){

        System.out.println("------+------+------+------+------+------+------+------+");
        System.out.println("------+------+ LIBROS BUSCADOS +------+------+");
        librosP = repositorio.findAll();

        AtomicInteger contador = new AtomicInteger(1);

        librosP.stream()
                        .map(l -> String.format("%d .- %s, # descargas: %d",contador.getAndIncrement(), l.getTitulo(), l.getDescargas()))
                                .forEach(System.out::println);

        System.out.println("------+------+------+------+------+------+------+------+");
        System.out.println("------+------+------+------+------+------+------+------+");

    }

    private Autores guardarAutor(Autor autorR){

        Optional<Autores> autoOpt = repositorioAutor.findByNombre(autorR.nombre());

        Autores autor;
        if (autoOpt.isPresent()) {
            autor = autoOpt.get();  // Autor ya existe
            System.out.println("El autor ya existe");
        } else {
            autor = new Autores(autorR.nombre(), autorR.birth_year(), autorR.death_year());
            repositorioAutor.save(autor);
            System.out.println("Autor Creado");
        }

        return autor;
    }

    private void guardarLibro(DatosLibro libroRecord, Autores autor){

        Optional<Libros> libroOp = repositorio.findByTitulo(libroRecord.title());
        Libros libro;

        if(libroOp.isPresent()){
            System.out.println("El libro ya existe");
        }else {
            repositorio.save(new Libros(libroRecord, autor));
            System.out.println("Libro Guardado");
        }

        System.out.println("------+------+------+------+------+------+------+------+");
        System.out.println("------+------+------+------+------+------+------+------+");
    }

}
