package com.sonidovivo.sonidovivo_api.controller;


import com.sonidovivo.sonidovivo_api.model.Usuario;
import com.sonidovivo.sonidovivo_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        List<Usuario> usuarios = service.getUsuarios();

        if(usuarios.isEmpty())
            return ResponseEntity.noContent().build(); // 204
        else
            return ResponseEntity.ok(usuarios); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
        Optional<Usuario> usuario = service.getUsuario(id);

        if(usuario.isPresent())
            return ResponseEntity.ok(usuario.get()); // 200
        else
            return ResponseEntity.notFound().build(); // 404
    }

    @PostMapping
    public ResponseEntity<Usuario> agregarUsuario(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = service.saveUsuario(usuario);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> editarUsuario(@RequestBody Usuario usuario, @PathVariable Long id){
        Optional<Usuario> existe = service.getUsuario(id);

        if(existe.isEmpty())
            return ResponseEntity.notFound().build(); // 404

        usuario.setId(id);

        Usuario actualizado = service.saveUsuario(usuario);
        return ResponseEntity.ok(actualizado); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        try {
            service.deleteUsuario(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (Exception e){
            return ResponseEntity.notFound().build(); // 404
        }
    }
}