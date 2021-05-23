package com.cavalcanti.hotelmanager.service;

import com.cavalcanti.hotelmanager.dtos.GuestDTO;
import com.cavalcanti.hotelmanager.models.Guest;

public interface GuestService {
	
	Iterable<GuestDTO> getAllGuests();
	
	GuestDTO getGuestByCpf(String cpf);
	
	GuestDTO getGuestByPhone(String phone);
	
	GuestDTO getGuestByName(String name);
	
	Guest saveGuest(Guest guest);
	
	void deleteGuest(String guestCpf);
	
	Guest updateGuest(String cpf, String name, String phone);
}
