package com.sonidovivo.sonidovivo_api.service;

import com.sonidovivo.sonidovivo_api.model.Usuario;
import com.sonidovivo.sonidovivo_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> getUsuarios(){
        return repository.findAll();
    }

    public Optional<Usuario> getUsuario(Long id){
        return repository.findById(id);
    }

    public Usuario saveUsuario(Usuario usuario){
        return repository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuario){
        Optional<Usuario> existe = getUsuario(id);

        if(existe.isEmpty()) {
            throw new RuntimeException("No encontrado");
        } else {
            return repository.save(usuario);
        }
    }

    public void deleteUsuario(Long id){
        if(repository.existsById(id))
            repository.deleteById(id);
        else
            throw new RuntimeException("No encontrado");
    }
}