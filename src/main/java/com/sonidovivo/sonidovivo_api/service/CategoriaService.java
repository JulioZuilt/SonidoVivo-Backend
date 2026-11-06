package com.sonidovivo.sonidovivo_api.service;

import com.sonidovivo.sonidovivo_api.model.Categoria;
import com.sonidovivo.sonidovivo_api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> getCategorias(){
        return repository.findAll();
    }

    public Optional<Categoria> getCategoria(Long id){
        return repository.findById(id);
    }

    public Categoria saveCategoria(Categoria categoria){
        return repository.save(categoria);
    }

    public Categoria updateCategoria(Long id, Categoria categoria){
        Optional<Categoria> existe = getCategoria(id);

        if(existe.isEmpty()) {
            throw new RuntimeException("No encontrado");
        } else {
            return repository.save(categoria);
        }
    }

    public void deleteCategoria(Long id){
        if(repository.existsById(id))
            repository.deleteById(id);
        else
            throw new RuntimeException("No encontrado");
    }
}