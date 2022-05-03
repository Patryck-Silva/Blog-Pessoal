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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //create table
@Table(name = "tb_tema")
public class Tema {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@NotBlank(message="a descricao Deve ser preenchido")
@Size(min = 10, max=200,message = "O texto deve conter no minímo 10 e no máximo 200 caracteres.")
private String descricao;

//relacionamento
@OneToMany(mappedBy ="tema",cascade = CascadeType.REMOVE)
@JsonIgnoreProperties("tema")
private List<Postagem> postagem;

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getDescricao() {
	return descricao;
}

public void setDescricao(String descricao) {
	this.descricao = descricao;
}

public List<Postagem> getPostagem() {
	return postagem;
}

public void setPostagem(List<Postagem> postagem) {
	this.postagem = postagem;
}

}
