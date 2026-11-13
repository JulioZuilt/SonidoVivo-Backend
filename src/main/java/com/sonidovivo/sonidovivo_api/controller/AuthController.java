package com.sonidovivo.sonidovivo_api.controller;

import com.sonidovivo.sonidovivo_api.model.Usuario;
import com.sonidovivo.sonidovivo_api.repository.UsuarioRepository;
import com.sonidovivo.sonidovivo_api.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    //verificamos con usuarioRepository que el correo no exista ya en la base de datos
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "El correo ya está registrado"));
        }
    //utilizamos passwordEncoder.encode para transformar la contraseña en hash antes de guardarla
    //llamamos al jwtservice.generartoken y entregamos su primer token al usuario recien registrado
        usuario.setPasswordHash(passwordEncoder.encode(usuario.getPasswordHash()));
        Usuario guardado = usuarioRepository.save(usuario);
        String token = jwtService.generarToken(guardado.getCorreo());

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "token", token,
                "correo", guardado.getCorreo()
        ));
    }

    //buscamos su usuario por su correo
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String correo = credenciales.get("correo");
        String password = credenciales.get("password");

        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);

    //ocupamos passwordEncoder.matches para comprobar si coinciden sin desencriptar el hash
        if (usuarioOpt.isEmpty() || !passwordEncoder.matches(password, usuarioOpt.get().getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales inválidas"));
        }

        String token = jwtService.generarToken(correo);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "correo", correo
        ));
    }
}