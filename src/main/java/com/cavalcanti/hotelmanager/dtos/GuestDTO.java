package com.cavalcanti.hotelmanager.dtos;

import lombok.Data;

@Data
public class GuestDTO {
	private String cpf;
	private String name;
	private String phone;
	
	public GuestDTO(String cpf, String name, String phone) {
		this.cpf = cpf;
		this.name = name;
		this.phone = phone;
	}
}
