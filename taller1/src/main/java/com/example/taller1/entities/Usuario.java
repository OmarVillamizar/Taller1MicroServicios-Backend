package com.example.taller1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Usuario {

	private String nombre;
	
	@Id
	private String cedula;
	
	private String correo;
	
	private String numero;
	

}
