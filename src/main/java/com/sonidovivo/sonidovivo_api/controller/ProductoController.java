package com.sonidovivo.sonidovivo_api.controller;

import com.sonidovivo.sonidovivo_api.model.Producto;
import com.sonidovivo.sonidovivo_api.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<List<Producto>> listar(){
        List<Producto> productos = service.getProductos();

        if(productos.isEmpty())
            return ResponseEntity.noContent().build(); // 204
        else
            return ResponseEntity.ok(productos); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id){
        Optional<Producto> producto = service.getProducto(id);

        if(producto.isPresent())
            return ResponseEntity.ok(producto.get()); // 200
        else
            return ResponseEntity.notFound().build(); // 404
    }

    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@Valid @RequestBody Producto producto){
        Producto nuevoProducto = service.saveProducto(producto);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> editarProducto(@Valid @RequestBody Producto producto, @PathVariable Long id){
        Optional<Producto> existe = service.getProducto(id);

        if(existe.isEmpty())
            return ResponseEntity.notFound().build(); // 404

        producto.setId(id);

        Producto actualizado = service.saveProducto(producto);
        return ResponseEntity.ok(actualizado); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        try {
            service.deleteProducto(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (Exception e){
            return ResponseEntity.notFound().build(); // 404
        }
    }
}