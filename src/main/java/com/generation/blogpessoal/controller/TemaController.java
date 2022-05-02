package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.repository.TemaRepository;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class TemaController {
	
	@Autowired
	private TemaRepository temasRepository;
	
	@GetMapping
	public ResponseEntity <List<Tema>> getAll(){
		return ResponseEntity.ok(temasRepository.findAll());
		// select * from tb_postagem
		}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema>getById(@PathVariable Long id){
		return temasRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());	
		}
	
	@GetMapping("/resumo_desc/{resumo_desc}")
	public ResponseEntity <List<Tema>> getByDesc (@PathVariable String resumo_desc){
		return ResponseEntity.ok(temasRepository.findByDescContainingIgnoreCase(resumo_desc));
	}
	}
