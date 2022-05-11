package com.generation.blogpessoal.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity//create table
@Table(name = "tb_postagem")// mudando nome para tb_postagem
public class Postagem {
	@Id//primary key id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private	Long id;
	@NotBlank(message = "O atributo título é obrigatório!") //não nulo e não branco
	@Size(min = 5,max = 100, message = "O título deve conter no mínino 5 e no máximo 100 caracteres.")
	private	String titulo;
	
	@NotNull(message = "O texto é obrigatório!")
	@Size(min = 10, max = 1000, message = "O texto deve conter no minímo 10 e no máximo 1000 caracteres.")
	private	String texto;
	
	@UpdateTimestamp
	private	LocalDate data;
	
	//relacionamento com temas
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	//getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
		
	
	
}
