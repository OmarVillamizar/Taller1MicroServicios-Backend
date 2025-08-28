package com.example.taller1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.taller1.entities.Usuario;
import com.example.taller1.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @DeleteMapping("/{cedula}")
    public void eliminarUsuario(@PathVariable String cedula) {
        usuarioService.eliminarUsuario(cedula);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }
    
    @GetMapping("/{cedula}")
    public Usuario obtenerUsuarioPorCedula(@PathVariable String cedula) {
        return usuarioService.obtenerUsuarioPorCedula(cedula);
    }

    @PutMapping("/{cedula}")
    public Usuario actualizarUsuario(@PathVariable String cedula, @RequestBody Usuario usuarioActualizado) {
        return usuarioService.actualizarUsuario(cedula, usuarioActualizado);
    }
}