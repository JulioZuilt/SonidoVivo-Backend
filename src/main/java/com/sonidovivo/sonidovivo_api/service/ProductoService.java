package com.sonidovivo.sonidovivo_api.service;

import com.sonidovivo.sonidovivo_api.model.Producto;
import com.sonidovivo.sonidovivo_api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public List<Producto> getProductos(){
        return repository.findAll();
    }

    public Optional<Producto> getProducto(Long id){
        return repository.findById(id);
    }

    public Producto saveProducto(Producto producto){
        return repository.save(producto);
    }

    public Producto updateProducto(Long id, Producto producto){
        Optional<Producto> existe = getProducto(id);

        if(existe.isEmpty()) {
            throw new RuntimeException("No encontrado");
        } else {
            return repository.save(producto);
        }
    }

    public void deleteProducto(Long id){
        if(repository.existsById(id))
            repository.deleteById(id);
        else
            throw new RuntimeException("No encontrado");
    }
}