package com.sonidovivo.sonidovivo_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String run;

    private String nombre;

    private String apellido;

    private String correo;

    private String telefono;

    private String direccion;

    @Column(name = "comuna_id")
    private Long comunaId;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "password_hash")
    private String passwordHash;

    private String salt;

    private String rol = "CLIENTE";

    private Boolean activo;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @PrePersist
    public void prePersist(){
        if (this.fechaRegistro == null){
            this.fechaRegistro = LocalDate.now();
        }
        if (this.rol == null){
            this.rol = "CLIENTE";
        }
        if (this.activo == null){
            this.activo = true;
        }
    }
}
