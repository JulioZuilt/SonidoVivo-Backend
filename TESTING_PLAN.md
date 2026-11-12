# Plan de Pruebas - SonidoVivo

## Objetivo

Validar el correcto funcionamiento de las entidades del backend SonidoVivo mediante pruebas unitarias utilizando JUnit 5 y generación de datos ficticios con DataFaker.

---

## Herramientas

- Java 17
- Spring Boot
- JUnit 5
- DataFaker

---

# Casos de Prueba

## CP-01: Registrar Usuario

- **Método:** `SonidoVivoTest.registrarUsuario()`
- **Objetivo:** Verificar que un objeto `Usuario` pueda instanciarse y poblarse correctamente con información válida generada por DataFaker.
- **Resultado Esperado:**
  - El objeto `Usuario` debe existir (`assertNotNull`).
  - El nombre asignado al usuario debe coincidir con el valor esperado (`assertEquals`).

## CP-02: Registrar Categoría

- **Método:** `SonidoVivoTest.registrarCategoria()`
- **Objetivo:** Verificar que una categoría de instrumentos/audio pueda crearse correctamente con nombre y descripción válidos.
- **Resultado Esperado:**
  - El objeto `Categoria` debe existir (`assertNotNull`).
  - El nombre de la categoría debe coincidir con el valor asignado (`assertEquals`).

## CP-03: Registrar Marca

- **Método:** `SonidoVivoTest.registrarMarca()`
- **Objetivo:** Verificar que una marca fabricante pueda crearse correctamente con información válida.
- **Resultado Esperado:**
  - El objeto `Marca` debe existir (`assertNotNull`).
  - El nombre de la marca debe coincidir con el valor asignado (`assertEquals`).

## CP-04: Registrar Producto

- **Método:** `SonidoVivoTest.registrarProducto()`
- **Objetivo:** Verificar que un producto pueda crearse con nombre, descripción, precio, stock e identificadores de categoría y marca asociados.
- **Resultado Esperado:**
  - El objeto `Producto` debe existir (`assertNotNull`).
  - El nombre del producto debe coincidir con el valor asignado (`assertEquals`).

---

## Resumen de Casos de Prueba

| ID    | Caso de Prueba      | Objetivo                                                                 | Resultado Esperado                                                   | Tipo     |
| ----- | ------------------- | ------------------------------------------------------------------------ | -------------------------------------------------------------------- | -------- |
| CP-01 | Registrar Usuario   | Validar la creación y asignación de datos de un Usuario con DataFaker.   | El usuario existe (`assertNotNull`) y su nombre coincide.            | Unitaria |
| CP-02 | Registrar Categoría | Validar la creación de una Categoría con nombre y descripción válidos.   | La categoría existe (`assertNotNull`) y su nombre coincide.          | Unitaria |
| CP-03 | Registrar Marca     | Validar la creación de una Marca con nombre válido.                      | La marca existe (`assertNotNull`) y su nombre coincide.              | Unitaria |
| CP-04 | Registrar Producto  | Validar la creación de un Producto con precio, stock e IDs asociados.    | El producto existe (`assertNotNull`) y su nombre coincide.           | Unitaria |