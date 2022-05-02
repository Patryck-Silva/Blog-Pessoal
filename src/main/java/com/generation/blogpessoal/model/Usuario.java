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
@Table(name = "tb_usuario")
public class Usuario {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@NotBlank(message = "O nome deve ser preenchido")
@Size(min = 1, max = 255, message = "O nome deve conter no mínimo 1 caracter e no máximo 255")
private String nome;

@NotBlank(message="O email deve ser preenchido")
@Size(min=1,max=255,message="O email deve conter no mínimo 1 caracter e no máximo 255")
private String email;

@NotBlank(message = "O username deve ser preenchido")
@Size(min=1,max=255,message="O username deve conter no mínimo 1 caracter e no máximo 255")
private String user;

@NotBlank(message = "A senha deve ser preenchida")
@Size(min=1,max=255,message="A senha deve conter no mínimo 1 caracter e no máximo 255")
private String senha;

@Size(min=1,max=255,message= "o endereço da foto deve conter no mínimo 1 caracter e no máximo 255")
private String foto;

@Size(min=1,max=500,message = "A bio deve conter no mínimo 1 caracter e no máximo 500")
private String bio;

	
//relacionamento 
@OneToMany(mappedBy = "usuario",cascade = CascadeType.REMOVE)
@JsonIgnoreProperties("usuario")
private List<Postagem> postagem;



//getters and setters
public long getId() {
	return id;
}


public void setId(long id) {
	this.id = id;
}


public String getNome() {
	return nome;
}


public void setNome(String nome) {
	this.nome = nome;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getUser() {
	return user;
}


public void setUser(String user) {
	this.user = user;
}


public String getSenha() {
	return senha;
}


public void setSenha(String senha) {
	this.senha = senha;
}


public String getFoto() {
	return foto;
}


public void setFoto(String foto) {
	this.foto = foto;
}


public String getBio() {
	return bio;
}


public void setBio(String bio) {
	this.bio = bio;
}


public List<Postagem> getPostagem() {
	return postagem;
}


public void setPostagem(List<Postagem> postagem) {
	this.postagem = postagem;
}
}
