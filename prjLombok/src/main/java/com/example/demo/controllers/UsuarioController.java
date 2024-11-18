package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Usuario;
import com.example.demo.services.UsuarioService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/usuario") 
public class UsuarioController { 
	private final UsuarioService usuarioservice; 
	@Autowired 
	public UsuarioController (UsuarioService usuarioservice) { 
		this.usuarioservice = usuarioservice;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscaUsuarioControlId(@PathVariable Long id) { 
		Usuario usuario = usuarioservice.buscaUsuariold(id); 
		if (usuario != null) { 
			return ResponseEntity.ok(usuario);
		}
		else { return ResponseEntity.notFound().build(); 
	}
	}
	
	@PostMapping("/") 
	public ResponseEntity<Usuario> salvaUsuariosControl (@RequestBody @Valid Usuario Usuario) { 
		Usuario salvaUsuario = usuarioservice.salvaUsuario(Usuario);
		return ResponseEntity.status (HttpStatus.CREATED).body(salvaUsuario);
	}
	
		@PutMapping("/{id}") 
		public ResponseEntity<Usuario> alteraUsuarioControl (@PathVariable Long id, @RequestBody @Valid Usuario Usuario) {
			 Usuario alteraUsuario = usuarioservice.alterarUsuario(id, Usuario); 
			 if (alteraUsuario != null) { 
				 return ResponseEntity.ok(Usuario);
			 }
			 else { 
				 return ResponseEntity.notFound().build();
			 }
		}
		@DeleteMapping("/{id}") 
		public ResponseEntity<String> apagaUsuarioControl (@PathVariable Long id) { 
			boolean apagar = usuarioservice.apagarUsuario(id); 
			if (apagar) { 
				return ResponseEntity.ok().body("O Usuario foi excluido com sucesso");
			} else { 
				return ResponseEntity.notFound().build();
			}
	}		
}