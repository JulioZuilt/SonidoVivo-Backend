package com.sonidovivo.sonidovivo_api.controller;

import com.sonidovivo.sonidovivo_api.model.Marca;
import com.sonidovivo.sonidovivo_api.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/marcas")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @GetMapping
    public ResponseEntity<List<Marca>> listar(){
        List<Marca> marcas = service.getMarcas();

        if(marcas.isEmpty())
            return ResponseEntity.noContent().build(); // 204
        else
            return ResponseEntity.ok(marcas); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarPorId(@PathVariable Long id){
        Optional<Marca> marca = service.getMarca(id);

        if(marca.isPresent())
            return ResponseEntity.ok(marca.get()); // 200
        else
            return ResponseEntity.notFound().build(); // 404
    }

    @PostMapping
    public ResponseEntity<Marca> agregarMarca(@RequestBody Marca marca){
        Marca nuevaMarca = service.saveMarca(marca);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body(nuevaMarca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> editarMarca(@RequestBody Marca marca, @PathVariable Long id){
        Optional<Marca> existe = service.getMarca(id);

        if(existe.isEmpty())
            return ResponseEntity.notFound().build(); // 404

        marca.setId(id);

        Marca actualizado = service.saveMarca(marca);
        return ResponseEntity.ok(actualizado); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id){
        try {
            service.deleteMarca(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (Exception e){
            return ResponseEntity.notFound().build(); // 404
        }
    }
}