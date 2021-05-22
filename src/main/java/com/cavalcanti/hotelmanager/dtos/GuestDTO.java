package com.cavalcanti.hotelmanager.dtos;

import com.cavalcanti.hotelmanager.models.Guest;

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

	public Guest fromDtoToEntity() {
		return new Guest(cpf, name, phone);
	}

}
