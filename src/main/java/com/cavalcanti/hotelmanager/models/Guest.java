package com.cavalcanti.hotelmanager.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Guest {
	
	@Id
	@CPF
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
