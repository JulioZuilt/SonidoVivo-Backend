package com.sonidovivo.sonidovivo_api.repository;

import com.sonidovivo.sonidovivo_api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository
        extends JpaRepository<Categoria, Long> {
}
