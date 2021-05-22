package com.cavalcanti.hotelmanager.dtos.mappers;

import com.cavalcanti.hotelmanager.dtos.GuestDTO;
import com.cavalcanti.hotelmanager.models.Guest;

public class GuestDTOMapper {
	
	public static Guest fromDtoToEntity(GuestDTO dto) {
		return new Guest(dto.getCpf(), dto.getName(), dto.getPhone());
	}
	
	public static GuestDTO fromEntityToDto(Guest guest) {
		return new GuestDTO(guest.getCpf(), guest.getName(), guest.getPhone());
	}
}
