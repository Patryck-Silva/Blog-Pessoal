package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;


@RestController
@RequestMapping("/postagem")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository postagensRepository;
	
	@GetMapping
	public ResponseEntity <List<Postagem>> getAll(){
		return ResponseEntity.ok(postagensRepository.findAll());
		// select * from tb_postagem
	}
}
