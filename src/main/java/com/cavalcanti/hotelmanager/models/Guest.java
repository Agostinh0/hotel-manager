package com.cavalcanti.hotelmanager.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import lombok.NoArgsConstructor;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@Data
public class Guest {
	
	@Id
	@CPF(message = "Invalid CPF!")
	private String cpf;
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@NotNull
	@Column(name = "phone")
	private String phone;
	
	@OneToMany(
		mappedBy = "guest",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<Stay> stays;
	
	public Guest(@CPF String cpf, String name, String phone) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.phone = phone;
	}
}
