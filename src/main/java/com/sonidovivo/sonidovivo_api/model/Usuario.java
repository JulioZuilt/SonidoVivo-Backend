package com.sonidovivo.sonidovivo_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El RUN es obligatorio")
    private String run;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String apellido;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es válido")
    private String correo;

    @Size(max = 20, message = "El télefono no puede superar los 20 caracteres")
    private String telefono;

    @Size(max = 255, message = "La dirección no puede superar los 255 caracteres")
    private String direccion;

    @Column(name = "comuna_id")
    private Long comunaId;

    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
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
