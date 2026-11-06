package com.sonidovivo.sonidovivo_api.service;

import com.sonidovivo.sonidovivo_api.model.Marca;
import com.sonidovivo.sonidovivo_api.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository repository;

    public List<Marca> getMarcas(){
        return repository.findAll();
    }

    public Optional<Marca> getMarca(Long id){
        return repository.findById(id);
    }

    public Marca saveMarca(Marca marca){
        return repository.save(marca);
    }

    public Marca updateMarca(Long id, Marca marca){
        Optional<Marca> existe = getMarca(id);

        if(existe.isEmpty()) {
            throw new RuntimeException("No encontrado");
        } else {
            return repository.save(marca);
        }
    }

    public void deleteMarca(Long id){
        if(repository.existsById(id))
            repository.deleteById(id);
        else
            throw new RuntimeException("No encontrado");
    }
}