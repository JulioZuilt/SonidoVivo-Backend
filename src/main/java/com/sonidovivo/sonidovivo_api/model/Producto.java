package com.sonidovivo.sonidovivo_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "categoria_id")
    private Long categoriaId;

    @Column(name = "marca_id")
    private Long marcaId;

    private String nombre;

    private String descripcion;

    private Double precio;

    private Integer stock;

    @Column(name = "imagen_url")
    private String imagenUrl;

    private Boolean activo;
}
