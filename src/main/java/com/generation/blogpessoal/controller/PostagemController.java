package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;


@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository postagensRepository;
	
	@GetMapping
	public ResponseEntity <List<Postagem>> getAll(){
		return ResponseEntity.ok(postagensRepository.findAll());
		// select * from tb_postagem
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Postagem>getById(@PathVariable Long id){
		return postagensRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}//select * form tb_postagem where id = id
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity <List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagensRepository.findByTituloContainingIgnoreCase(titulo));
	}
	@PutMapping 
	public ResponseEntity <Postagem> putPostagem (@Valid @RequestBody Postagem postagem){
		return postagensRepository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(postagensRepository.save(postagem)))
				.orElse(ResponseEntity.notFound().build());
	}
	@PostMapping 
	public ResponseEntity <Postagem> postPostagem (@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(postagensRepository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void deletepostagem(@PathVariable Long id) {
		Optional<Postagem> postagem = postagensRepository.findById(id);
		
		if(postagem.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	
			postagensRepository.deleteById(id);
		
	}
}
