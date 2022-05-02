package com.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //create table
@Table(name = "tb_tema")
public class Tema {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@NotBlank(message = "A descrição é obrigatória")
private String resumo_desc;

@NotNull
@Size(min = 10, max=200,message = "O texto deve conter no minímo 10 e no máximo 200 caracteres.")
private String descricao;
//relacionamento
@OneToMany(mappedBy ="tema",cascade = CascadeType.REMOVE)
@JsonIgnoreProperties("tema")
private List<Postagem> postagem;

//getters and setters
public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getDescricao() {
	return resumo_desc;
}

public void setDescricao(String descricao) {
	this.resumo_desc= descricao;
}

public List<Postagem> getPostagem() {
	return postagem;
}

public void setPostagem(List<Postagem> postagem) {
	this.postagem = postagem;
}

public String getResumo_desc() {
	return resumo_desc;
}

public void setResumo_desc(String resumo_desc) {
	this.resumo_desc = resumo_desc;
}


	
}
