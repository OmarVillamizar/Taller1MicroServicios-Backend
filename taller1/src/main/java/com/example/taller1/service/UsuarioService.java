package com.example.taller1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.taller1.entities.Usuario;
import com.example.taller1.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public Usuario guardarUsuario(Usuario usuario) {
    	
        // Llamada al microservicio FastAPI antes de guardar
    	String url = "http://validador:8001/validar-correo/" + usuario.getCorreo();
        ValidacionResponse response = restTemplate.getForObject(url, ValidacionResponse.class);
        ///////////////////////////
        
        if (response != null && response.isValido()) {
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("❌❌ Correo inválido según microservicio FastAPI. ❌❌");
        }
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

    // DTO para mapear la respuesta JSON de FastAPI
    static class ValidacionResponse {
        private String correo;
        private boolean valido;

        public String getCorreo() { return correo; }
        public void setCorreo(String correo) { this.correo = correo; }

        public boolean isValido() { return valido; }
        public void setValido(boolean valido) { this.valido = valido; }
    }
}
