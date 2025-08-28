package com.example.taller1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taller1.entities.Usuario;
import com.example.taller1.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(String cedula) {
        usuarioRepository.deleteById(cedula);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    
    public Usuario obtenerUsuarioPorCedula(String cedula) {
        return usuarioRepository.findById(cedula).orElse(null);
    }

    public Usuario actualizarUsuario(String cedula, Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(cedula).orElse(null);
        if (usuarioExistente != null) {
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
            usuarioExistente.setNumero(usuarioActualizado.getNumero());
            return usuarioRepository.save(usuarioExistente);
        }
        return null;
    }
}