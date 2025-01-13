# Challenge LiterALURA

## Objetivo

El objetivo es crear un Catálogo de Libros interactivo que permita la comunicación textual con los usuarios a través de la consola, ofreciendo al menos cinco opciones de interacción. Los libros serán obtenidos mediante una API especializada, y se proporcionará toda la información relacionada con la API, así como las opciones de interacción en la columna "Backlog" o "Listo para iniciar".

## Pasos para el Desarrollo

1. **Configuración del entorno Java.**
2. **Creación del proyecto.**
3. **Consumo de la API** para obtener datos sobre los libros.
4. **Análisis de la respuesta JSON** proveniente de la API.
5. **Inserción y consulta** de los datos en la base de datos.
6. **Exhibición de resultados** interactivos a los usuarios.

## Funcionalidad

### 1. Menú

El sistema cuenta con un menú interactivo que ofrece 6 opciones principales:

### 2. Búsqueda de libros por título

- Permite buscar un libro ingresando su título.
- Realiza una consulta a la API **Gutendex** para obtener información del libro.


### 3. Listado de libros en base de datos

- Muestra todos los libros almacenados en la base de datos


### 4. Listado de autores

- Presenta un listado de autores registrados

### 5. Consulta por año

### 6. Consulta por idioma

- Presenta un menú para seleccionar un idioma


### 7. Top 10 de los libros más descargados
- Presenta una lista de los 10 cursos más descargados

### 8. Salida (CASE 0)

- Muestra un mensaje de despedida cuando se selecciona la opción para salir:


## Tecnologias utilizadas

- [Spring](https://start.spring.io)
- [IntelliJ](https://www.jetbrains.com/es-es/idea/)
- [jackson - databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
- [MySQL](https://www.mysql.com/)


