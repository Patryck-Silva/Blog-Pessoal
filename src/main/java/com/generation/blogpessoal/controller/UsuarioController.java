package com.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuariosRepository;
	
	@GetMapping
	public ResponseEntity <List<Usuario>> getAll(){
		return ResponseEntity.ok(usuariosRepository.findAll());
		//select * from tb_usuario
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Usuario> getById(@PathVariable Long id){
		return usuariosRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
			//select* from tb_usuario where id = id
	}
	
	@GetMapping("/user/{user}")
	public ResponseEntity <List<Usuario>> getByUser(@PathVariable String user){
		return ResponseEntity.ok(usuariosRepository.findByUserContainingIgnoreCase(user));
	}
	
	@PostMapping
	public ResponseEntity <Usuario>postUsuario(@Valid @RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuariosRepository.save(usuario));
	}
	
	@PutMapping
	public ResponseEntity <Usuario> putUsuario(@Valid @RequestBody Usuario usuario){
		return usuariosRepository.findById(usuario.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(usuariosRepository.save(usuario)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> deleteUsuario(@Valid @PathVariable Long id){
		return usuariosRepository.findById(id)
				.map(resposta -> {
					usuariosRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());

	}
}
