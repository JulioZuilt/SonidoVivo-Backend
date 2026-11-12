package com.sonidovivo.sonidovivo_api.controller;

import com.sonidovivo.sonidovivo_api.model.Categoria;
import com.sonidovivo.sonidovivo_api.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> listar(){
        List<Categoria> categorias = service.getCategorias();

        if(categorias.isEmpty())
            return ResponseEntity.noContent().build(); // 204
        else
            return ResponseEntity.ok(categorias); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id){
        Optional<Categoria> categoria = service.getCategoria(id);

        if(categoria.isPresent())
            return ResponseEntity.ok(categoria.get()); // 200
        else
            return ResponseEntity.notFound().build(); // 404
    }

    @PostMapping
    public ResponseEntity<Categoria> agregarCategoria(@Valid @RequestBody Categoria categoria){
        Categoria nuevaCategoria = service.saveCategoria(categoria);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body(nuevaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editarCategoria(@Valid @RequestBody Categoria categoria, @PathVariable Long id){
        Optional<Categoria> existe = service.getCategoria(id);

        if(existe.isEmpty())
            return ResponseEntity.notFound().build(); // 404

        categoria.setId(id);

        Categoria actualizado = service.saveCategoria(categoria);
        return ResponseEntity.ok(actualizado); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id){
        try {
            service.deleteCategoria(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (Exception e){
            return ResponseEntity.notFound().build(); // 404
        }
    }
}