package com.cavalcanti.hotelmanager.service;

import java.util.Optional;

import com.cavalcanti.hotelmanager.dtos.GuestDTO;
import com.cavalcanti.hotelmanager.models.Guest;

public interface GuestService {
	
	Iterable<GuestDTO> getAllGuests();
	
	Optional<GuestDTO> getGuestByCpf(String cpf);
	
	Optional<GuestDTO> getGuestByPhone(String phone);
	
	Optional<GuestDTO> getGuestByName(String name);
	
	Guest saveGuest(Guest guest);
	
	void deleteGuest(String guestCpf);
	
	Guest updateGuest(String cpf, String name, String phone);
}
