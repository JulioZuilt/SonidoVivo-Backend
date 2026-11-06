package com.sonidovivo.sonidovivo_api.repository;

import com.sonidovivo.sonidovivo_api.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository
        extends JpaRepository<Marca, Long> {
}
