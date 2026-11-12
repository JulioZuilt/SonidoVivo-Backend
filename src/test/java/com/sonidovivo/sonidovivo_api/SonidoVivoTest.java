package com.sonidovivo.sonidovivo_api;

import com.sonidovivo.sonidovivo_api.model.Categoria;
import com.sonidovivo.sonidovivo_api.model.Marca;
import com.sonidovivo.sonidovivo_api.model.Producto;
import com.sonidovivo.sonidovivo_api.model.Usuario;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SonidoVivoTest {

    @Test
    void registrarUsuario(){
        // ARRANGE (preparar)
        Usuario usuario = new Usuario();
        Faker faker = new Faker();

        // usuario.setNombre(faker.name().firstName());
        usuario.setNombre("Carlos");
        usuario.setApellido(faker.name().lastName());
        usuario.setCorreo(faker.internet().emailAddress());
        usuario.setRun(faker.number().digits(8) + "-" + faker.number().digit());
        usuario.setTelefono(faker.phoneNumber().cellPhone());
        usuario.setDireccion(faker.address().streetAddress());
        usuario.setComunaId(1L);
        usuario.setFechaNacimiento(LocalDate.of(1998, 4, 10));
        usuario.setPasswordHash(faker.internet().password(8, 16));

        // ACT (Ejecutar)
        System.out.println(usuario);

        // ASSERT
        assertNotNull(usuario);
        assertEquals("Carlos", usuario.getNombre());
    }

    @Test
    void registrarCategoria(){
        // ARRANGE (preparar)
        Categoria categoria = new Categoria();
        Faker faker = new Faker();

        // categoria.setNombre(faker.music().genre());
        categoria.setDescripcion(faker.lorem().sentence());
        categoria.setNombre("Guitarras Electricas");

        // ACT (Ejecutar)
        System.out.println(categoria);

        // ASSERT
        assertNotNull(categoria);
        assertEquals("Guitarras Electricas", categoria.getNombre());
    }

    @Test
    void registrarMarca(){
        // ARRANGE (preparar)
        Marca marca = new Marca();
        Faker faker = new Faker();

        // marca.setNombre(faker.company().name());
        marca.setNombre("Fender");

        // ACT (Ejecutar)
        System.out.println(marca);

        // ASSERT
        assertNotNull(marca);
        assertEquals("Fender", marca.getNombre());
    }

    @Test
    void registrarProducto(){
        // ARRANGE (preparar)
        Producto producto = new Producto();
        Faker faker = new Faker();

        // producto.setNombre(faker.music().instrument());
        producto.setDescripcion(faker.lorem().sentence());
        producto.setNombre("Guitarra Stratocaster");
        producto.setPrecio(Double.valueOf(faker.number().numberBetween(50000, 900000)));
        producto.setStock(faker.number().numberBetween(1, 30));
        producto.setCategoriaId(1L);
        producto.setMarcaId(1L);

        // ACT (Ejecutar)
        System.out.println(producto);

        // ASSERT
        assertNotNull(producto);
        assertEquals("Guitarra Stratocaster", producto.getNombre());
    }
}