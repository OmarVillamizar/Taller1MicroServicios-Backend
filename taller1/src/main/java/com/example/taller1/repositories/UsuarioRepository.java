package com.example.taller1.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taller1.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    
}