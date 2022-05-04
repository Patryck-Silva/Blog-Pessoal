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

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;


@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository postagensRepository;
	
	@Autowired
	private TemaRepository temarepository;
	
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
		if(postagensRepository.existsById(postagem.getId())) {
			if(temarepository.existsById(postagem.getTema().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(postagensRepository.save(postagem));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity .status(HttpStatus.NOT_FOUND).build();
	}
	@PostMapping 
	public ResponseEntity <Postagem> postPostagem (@Valid @RequestBody Postagem postagem){
		if(temarepository.existsById(postagem.getTema().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(postagensRepository.save(postagem));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	
	@DeleteMapping ("/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)//Para trazer o status sem conteúdo
    public ResponseEntity<Object> deletePostagem (@Valid @PathVariable Long id){
		return postagensRepository.findById(id)
				.map(resposta -> {
					postagensRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
    
    }
}
